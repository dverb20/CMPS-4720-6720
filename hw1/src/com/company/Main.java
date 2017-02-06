package com.company;
//import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String csvFile = "/Users/dannyverb/Desktop/iris.csv";
        //BufferedReader br = null;
        //String line = "";
        //String cvsSplitBy = ",";
        int count = 0;
        //must change size based on knowledge of input data
        double data[][] = new double[150][5];


        Scanner scanner = new Scanner(new File(csvFile));
        //scanner.useDelimiter(",");
        while(scanner.hasNext()){
            String nextLine = scanner.nextLine();
            String[] arr=nextLine.split(",");
            //System.out.println("first attribute: " + arr[0] + " , name=" + arr[4] + "]");
            for(int i=0; i<4; i++){
                data[count][i] = Double.parseDouble(arr[i]);
            }
            count++;
            //System.out.println(count);
        }
        scanner.close();

        Model setosa = new Model(4, 0.1);
        Model versicolor = new Model(4, 0.1);
        Model virginica = new Model(4, 0.1);

        //Training
        int answer;
        double sum;
        for(int i=0; i < 20; i++){
            //while training, find y by running model on example, use that to update
            // 0-40, 50-90, 100-140 for training
            int j = 0;
            while(j < 150){
                if(j < 40){ // setosa
                    answer = setosa.runSolve(data[j]);
                    sum = sumArr(data[j]);
                    if(answer < sum){
                        setosa.changeValues(data[j], 1);
                    }
                    else{
                        setosa.changeValues(data[j], -1);
                    }
                }
                else if(j > 49 && j<90) { // versicolor
                    answer = versicolor.runSolve(data[j]);
                    sum = sumArr(data[j]);
                    if(answer < sum){
                        versicolor.changeValues(data[j], 1);
                    }
                    else{
                        versicolor.changeValues(data[j], -1);
                    }
                }
                else if(j > 99){ // virginica
                    answer = virginica.runSolve(data[j]);
                    sum = sumArr(data[j]);
                    if(answer < sum){
                        virginica.changeValues(data[j], 1);
                    }
                    else{
                        virginica.changeValues(data[j], -1);
                    }
                }
                j++;
            }
        }
        System.out.println(setosa.toString());
        System.out.println(versicolor.toString());
        System.out.println(virginica.toString());

        //Testing
        int j = 0;
        while(j < 150){
            if(j > 39 && j<50){ // setosa
                answer = setosa.runSolve(data[j]);
                System.out.println(answer);
            }
            else if(j > 89 && j<100) { // versicolor
                answer = versicolor.runSolve(data[j]);
                System.out.println(answer);
            }
            else if(j>139 && j < 150){ // virginica
                answer = virginica.runSolve(data[j]);
                System.out.println(answer);
            }
            j++;
        }
        //System.out.println(data.length);

    }

    public static double sumArr(double[] values) {
        double result = 0;
        for (double value:values)
            result += value;
        return result;
    }
}

