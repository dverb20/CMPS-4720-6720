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

        Model perceptron = new Model(4, 0.2);
        for(int i=0; i < 20; i++){

        }
        //System.out.println(data.length);

    }
}

