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
            System.out.println(count);
        }
        scanner.close();

        Model setosa = new Model(4, 0.2);
        Model versicolor = new Model(4, 0.2);
        Model virginica = new Model(4, 0.2);
        for(int i=0; i < 20; i++){
            //while training, find y by running model on example, use that to update
            // 0-40, 50-90, 100-140 for training
            int j = 0;
            while(j < 150){
                if(j < 41){ // setosa
                    int answer = setosa.runSolve(data[j]);
                    //if statement using answer
                    setosa.changeValues(data[j]);
                }
                else if(j > 49 && j<91) { // versicolor
                    int answer = versicolor.runSolve(data[j]);
                    //if statement using answer
                    versicolor.changeValues(data[j]);
                }
                else if(j > 99){ // virginica
                    int answer = virginica.runSolve(data[j]);
                    //if statement using answer
                    virginica.changeValues(data[j]);
                }
                j++;
            }
        }
        //System.out.println(data.length);

    }
}

