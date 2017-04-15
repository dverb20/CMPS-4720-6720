package com.company;
//import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String csvFile = "../hw3_reader/movie_metadata.csv";
        //BufferedReader br = null;
        //String line = "";
        //String cvsSplitBy = ",";
        int count = 0;
        //must change size based on knowledge of input data
        ArrayList<String[]> data = new ArrayList<String[]>();
        String[] lexiconHeaders = {"director_name", "duration", "actors", "content_rating"};
        //duration will be grouped into blocks of 30 minutes
        //actors encompasses actor_#_name from 1-3
        int[] headerIndex = {1,3,-1,21};
        int[] actor_index = {6,10,14};
        int budgetIndex = 22;
        int grossIndex = 8;
        //actor indecies are 6, 10, 14

        Lexicon directors = new Lexicon("Directors");
        Lexicon duration = new Lexicon("Duration");
        Lexicon actors = new Lexicon("Actors");
        Lexicon rating = new Lexicon("Content_Rating");

        Scanner scanner = new Scanner(new File(csvFile));
        //scanner.useDelimiter(",");
        String[] header = scanner.nextLine().split(",");
        while(scanner.hasNext()){
            String nextLine = scanner.nextLine();
            String[] arr=nextLine.split(",");
            if(dataGate(arr, budgetIndex, grossIndex) == true){
                boolean success = successGate(Double.parseDouble(arr[budgetIndex]), Double.parseDouble(arr[grossIndex]));
                //boolean success = true;

                //Directors
                directors.addFactor(arr[headerIndex[0]], success, 1.0);

                //Duration
                int dur = (int) (Math.round(Double.parseDouble(arr[headerIndex[1]])/30) * 30);
                String roundDur = String.valueOf(dur);
                duration.addFactor(roundDur, success, 1.0);

                //Actors
                int iter = 0;
                while(iter < actor_index.length){
                    actors.addFactor(arr[actor_index[iter]], success, 1.0);
                    iter++;
                }

                //Content Rating
                rating.addFactor(arr[headerIndex[3]], success, 1.0);


                //Size will vary based on csv file, this case 0-27
                data.add(count, arr);
                count++;
            }


        }
        scanner.close();


//        int m = 0;
//        int end = data.size();
//        while(m < end){
//            System.out.println(data.get(m)[1]);
//            m = m+10;
//        }
        System.out.println(data.get(95)[1]);
        System.out.println(data.get(3)[1]);
        System.out.println(data.get(3)[2]);
        System.out.println(rating.toString());
        //System.out.println(count);
    }

    public static boolean successGate(double budget, double gross){
        double margin = gross/budget;
        if(margin > 0.8){
            return true;
        }
        else{
            return false;
        }
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
