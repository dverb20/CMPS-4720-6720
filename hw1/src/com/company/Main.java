package com.company;
//import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String csvFile = "../hw1/iris.csv";
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

        Model setosa = new Model(4, 0.02);
        Model versicolor = new Model(4, 0.02);
        Model virginica = new Model(4, 0.02);

        //Training
        int answer1;
        int answer2;
        int answer3;
        //while training, find y by running model on example, use that to update
        // 0-40, 50-90, 100-140 for training
        int j = 0;
        for(int i=0; i<3; i++){
            while(j < 150){
                if(j < 40){ // setosa
                    answer1 = setosa.runSolve(data[j]);
                    setosa.changeValues(data[j], answer1, 1);
                    answer2 = versicolor.runSolve(data[j]);
                    versicolor.changeValues(data[j], answer2, 0);
                    answer3 = virginica.runSolve(data[j]);
                    virginica.changeValues(data[j], answer3, 0);
                }
                else if(j > 49 && j<90) { // versicolor
                    answer1 = setosa.runSolve(data[j]);
                    setosa.changeValues(data[j], answer1, 0);
                    answer2 = versicolor.runSolve(data[j]);
                    versicolor.changeValues(data[j], answer2, 1);
                    answer3 = virginica.runSolve(data[j]);
                    virginica.changeValues(data[j], answer3, 0);
                }
                else if(j > 99){ // virginica
                    answer1 = setosa.runSolve(data[j]);
                    setosa.changeValues(data[j], answer1, 0);
                    answer2 = versicolor.runSolve(data[j]);
                    versicolor.changeValues(data[j], answer2, 0);
                    answer3 = virginica.runSolve(data[j]);
                    virginica.changeValues(data[j], answer3, 1);
                }
                j++;
            }
        }

        System.out.println(setosa.toString());
        System.out.println(versicolor.toString());
        System.out.println(virginica.toString());

        //Testing
        j = 0;
        while(j < 150){
            if(j > 39 && j<50){ // setosa
                answer1 = setosa.runSolve(data[j]);
                System.out.println(answer1);
            }
            else if(j > 89 && j<100) { // versicolor
                answer2 = versicolor.runSolve(data[j]);
                System.out.println(answer2);
            }
            else if(j>139 && j < 150){ // virginica
                answer3 = virginica.runSolve(data[j]);
                System.out.println(answer3);
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

