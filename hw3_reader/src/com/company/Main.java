package com.company;
//import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.InterruptedIOException;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String csvFile = "../hw3_reader/movie_metadata.csv";
        //BufferedReader br = null;
        //String line = "";
        //String cvsSplitBy = ",";
        int count = 0;
        //must change size based on knowledge of input data
        ArrayList<String[]> trainingData = new ArrayList<String[]>();
        ArrayList<String[]> testData = new ArrayList<String[]>();
        String[] lexiconHeaders = {"director_name", "duration", "actors", "content_rating"};
        //duration will be grouped into blocks of 30 minutes
        //actors encompasses actor_#_name from 1-3
        int[] headerIndex = {1,3,-1,21,9};
        int[] actor_index = {6,10,14};
        int budgetIndex = 22;
        int grossIndex = 8;
        //ArrayList<Double> margins = new ArrayList<Double>();
        //actor indecies are 6, 10, 14

        Lexicon directors = new Lexicon("Directors");
        Lexicon duration = new Lexicon("Duration");
        Lexicon actors = new Lexicon("Actors");
        Lexicon rating = new Lexicon("Content_Rating");
        Lexicon genre = new Lexicon("Genre");

        Scanner scanner = new Scanner(new File(csvFile));
        //scanner.useDelimiter(",");
        String[] header = scanner.nextLine().split(",");
        while(scanner.hasNext()){
            String nextLine = scanner.nextLine();
            String[] arr=nextLine.split(",");
            if(dataGate(arr, budgetIndex, grossIndex) == true){
                if(Integer.parseInt(arr[23]) < 2015){
                    double budget = Double.parseDouble(arr[budgetIndex]);
                    double gross = Double.parseDouble(arr[grossIndex]);
                    double margin = successGate(budget, gross);
                    //margins.add(margin);

                    //boolean success = true;

                    //Directors
                    if(directors.nameExists(arr[headerIndex[0]]) == false){
                        directors.addFactor(arr[headerIndex[0]], margin);
                    }
                    else{
                        directors.addAppearance(arr[headerIndex[0]]);
                    }


                    //Duration
                    int dur = (int) (Math.round(Double.parseDouble(arr[headerIndex[1]])/30) * 30);
                    String roundDur = String.valueOf(dur);
                    if(duration.nameExists(roundDur) == false){
                        duration.addFactor(roundDur, margin);
                    }
                    else{
                        duration.addAppearance(roundDur);
                    }

                    //Actors
                    int iter = 0;
                    while(iter < actor_index.length){
                        if(actors.nameExists(arr[actor_index[iter]]) == false){
                            actors.addFactor(arr[actor_index[iter]], margin);
                        }
                        else{
                            actors.addAppearance(arr[actor_index[iter]]);
                        }
                        iter++;
                    }

                    //Content Rating
                    if(rating.nameExists(arr[headerIndex[3]]) == false){
                        rating.addFactor(arr[headerIndex[3]], margin);
                    }
                    else{
                        rating.addAppearance(arr[headerIndex[3]]);
                    }

                    String delims = "[|]";
                    String[] genres = arr[headerIndex[4]].split(delims);
                    //System.out.println(Arrays.toString(genres));
                    for(String s : genres){
                        if(genre.nameExists(s) == false){
                            genre.addFactor(s, margin);
                        }
                    }

                    //Size will vary based on csv file, this case 0-27
                    trainingData.add(count, arr);
                    count++;
                }
                else{
                    testData.add(arr);
                }
            }


        }
        scanner.close();
//        Collections.sort(margins);
//        int size = margins.size();
//        System.out.println(margins.get((size/2)));
        String n = "Bryan Singer";
        for(int i=0; i<1; i++){
            for(String[] line : trainingData){
                String name = line[1];
                double budget = Double.parseDouble(line[budgetIndex]);
                double gross = Double.parseDouble(line[grossIndex]);
                double margin = successGate(budget, gross);
                int dur = (int) (Math.round(Double.parseDouble(line[headerIndex[1]])/30) * 30);
                String roundDur = String.valueOf(dur);
                String delims = "[|]";
                String[] genres = line[headerIndex[4]].split(delims);
//                double durationWeight = duration.getWeight(roundDur);
//                double prediction = (.4*directorWeight*(directorApp/5) + .2*actorWeight1*(actor1App/5) + .2*actorWeight2*(actor2App/5)
//                        + .2*actorWeight3*(actor3App/5) + .00*ratingWeight + .00*durationWeight);
                for(String s : genres){
                    genre.update(s, margin, budget);
                }
                actors.update(line[actor_index[0]], margin, budget);
                actors.update(line[actor_index[1]], margin, budget);
                actors.update(line[actor_index[2]], margin, budget);
                directors.update(line[headerIndex[0]], margin, budget);
                rating.update(line[headerIndex[3]], margin, budget);
                duration.update(roundDur, margin, budget);
                double actorWeight1 = actors.getWeight(line[actor_index[0]]);
                double actorWeight2 = actors.getWeight(line[actor_index[1]]);
                double actorWeight3 = actors.getWeight(line[actor_index[2]]);
                double directorWeight = directors.getWeight(line[headerIndex[0]]);
//                double actor1App = actors.getAppearances(line[actor_index[0]]);
//                double actor2App = actors.getAppearances(line[actor_index[1]]);
//                double actor3App = actors.getAppearances(line[actor_index[2]]);
//                double directorApp = directors.getAppearances(line[headerIndex[0]]);
                double ratingWeight = rating.getWeight(line[headerIndex[3]]);
//                if(name.equals(n)) {
//                    System.out.println("" + (double)Math.round(margin*1000)/1000);
//                    System.out.println("\t" + actorWeight1 + "\t" + actorWeight2 + "\t" + actorWeight3 + "\t" + directorWeight);
//                }
            }
        }
        System.out.println();
        int coun = 1;
        int correct1 = 0;
        int success1 = 0;
        int tenp1 = 0;
        int correct2 = 0;
        int success2 = 0;
        int tenp2 = 0;
        int correct3 = 0;
        int success3 = 0;
        int tenp3 = 0;
        for(String[] line : testData){
            double margin = successGate(Double.parseDouble(line[budgetIndex]), Double.parseDouble(line[grossIndex]));
            String name = line[1];
            double den = Math.log(20);
            String delims = "[|]";
            String[] genres = line[headerIndex[4]].split(delims);
            double[] gen = new double[genres.length];
            int i = 0;
            for(String s : genres){
                gen[i] = genre.getWeight(s);
                i++;
            }
            double actorWeight1 = actors.getWeight(line[actor_index[0]]);
            double actorWeight2 = actors.getWeight(line[actor_index[1]]);
            double actorWeight3 = actors.getWeight(line[actor_index[2]]);
            double directorWeight = directors.getWeight(line[headerIndex[0]]);
            double actor1App = actors.getAppearances(line[actor_index[0]]);
            double actor2App = actors.getAppearances(line[actor_index[1]]);
            double actor3App = actors.getAppearances(line[actor_index[2]]);
            double directorApp = directors.getAppearances(line[headerIndex[0]]);
            double sum = actor1App+actor2App+actor3App+directorApp;
            double prediction1 = (actorWeight1+actorWeight2+actorWeight3+directorWeight)/4;
            double prediction2 = ((actorWeight1*(actor1App/sum))+(actorWeight2*(actor2App/sum))+(actorWeight3*(actor3App/sum))+(directorWeight*(directorApp/sum)));
            double total = 0;
            for(double d : gen){
                total += d;
            }
            double prediction3 = (total + actorWeight1+actorWeight2+actorWeight3+directorWeight)/(gen.length+4);
            double change1 = Math.exp(prediction1*den)/10;
            double change2 = Math.exp(prediction2*den)/10;
            double change3 = Math.exp(prediction3*den)/10;
            if(change1 > 0.75 && margin > 0.75){
                correct1++;
                success1++;
            }
            else if(margin > 0.75 && change1 < 0.75){
                success1++;
            }
            else if(change1 < 0.75 && margin < 0.75){
                correct1++;
            }
            if(Math.abs(margin-change2)/margin < 0.2){
                tenp1++;
                //correct1++;
            }

            if(change2 > 0.75 && margin > 0.75){
                correct2++;
                success2++;
            }
            else if(margin > 0.75 && change2 < 0.75){
                success2++;
            }
            else if(change2 < 0.75 && margin < 0.75){
                correct2++;
            }
            if(Math.abs(margin-change2)/margin < 0.2){
                tenp2++;
                //correct2++;
            }

            if(change3 > 0.75 && margin > 0.75){
                correct3++;
                success3++;
            }
            else if(margin > 0.75 && change3 < 0.75){
                success3++;
            }
            else if(change3 < 0.75 && margin < 0.75){
                correct3++;
            }
            if(Math.abs(margin-change3)/margin < 0.2){
                tenp3++;
                //correct3++;
            }

            //System.out.println((actorWeight1*(actor1App/sum)));
            if(name.equals(n)) {
                System.out.println("" + ((double)Math.round(margin*1000)/1000) + "\t" + Math.exp(prediction3*den)/10 + "\t" + "\t" + Math.exp(prediction2*den)/10);
                System.out.println("\t" + actorWeight1 + "\t" + actorWeight2 + "\t" + actorWeight3 + "\t" + directorWeight + "\t" + gen[0]);
            }
            //System.out.println(name + " ; " + (double)Math.round(margin*1000)/1000 + " : " + (double)Math.round(prediction*1000)/1000);
            coun++;
        }
        System.out.println("------------------------");
        System.out.println("Total Training Data: " + count);
        System.out.println("Total Testing Data: " + coun);
        System.out.println("Total Correct: " + correct1);
        System.out.println("Total Success: " + success1);
        System.out.println("Total within 10%: " + tenp1);

        System.out.println("------------------------");
        System.out.println("Total Training Data: " + count);
        System.out.println("Total Testing Data: " + coun);
        System.out.println("Total Correct: " + correct2);
        System.out.println("Total Success: " + success2);
        System.out.println("Total within 10%: " + tenp2);

        System.out.println("------------------------");
        System.out.println("Total Training Data: " + count);
        System.out.println("Total Testing Data: " + coun);
        System.out.println("Total Correct: " + correct3);
        System.out.println("Total Success: " + success3);
        System.out.println("Total within 10%: " + tenp3);

//        int m = 0;
//        int end = data.size();
//        while(m < end){
//            System.out.println(data.get(m)[1]);
//            m = m+10;
//        }
//        System.out.println(trainingData.get(95)[1]);
//        System.out.println(trainingData.get(11)[1]);
//        System.out.println(trainingData.get(3)[2]);
        //System.out.println(actors.toString());
        //System.out.println(count);
    }

    public static double successGate(double budget, double gross){
        double margin = gross/budget;
        return margin;
//        if(margin > 0.8){
//            return true;
//        }
//        else{
//            return false;
//        }
    }

    public static boolean dataGate(String[] line, int budget, int gross){


//        for(int i=0; i<28; i++){
//            if(line[i].isEmpty()){
//                return false;
//            }
//        }
//        return true;
//        if(line.length < 27){
//            return false;
//        }
//        else{
//            return true;
//        }
        for(String s : line){
            if(s.equals("")){
                return false;
            }
        }
        if(isDouble(line[budget]) == false || isDouble(line[gross]) == false){
            return false;
        }
        return true;
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
