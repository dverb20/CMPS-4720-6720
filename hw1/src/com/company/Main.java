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
        System.out.println(data.length);


//        CSVReader reader = new CSVReader(new FileReader(csvFile));
//        String [] nextLine;
//        while ((nextLine = reader.readNext()) != null) {
//            // nextLine[] is an array of values from the line
//            //System.out.println(nextLine[0] + nextLine[1] + "etc...");
//            System.out.println("first attribute: " + nextLine[0] + " , name=" + nextLine[4] + "]");
//            for(int i=0; i<4; i++){
//                data[count][i] = Double.parseDouble(nextLine[i]);
//            }
//            count++;
//            System.out.println(count);
//        }


//        try {
//
//            br = new BufferedReader(new FileReader(csvFile));
//            while ((line = br.readLine()) != null) {
//
//                // use comma as separator
//                String[] feature = line.split(cvsSplitBy);
//                System.out.println("first attribute: " + feature[0] + " , name=" + feature[4] + "]");
//                for(int i=0; i<4; i++){
//                    data[count][i] = Double.parseDouble(feature[i]);
//                }
//                count++;
//                System.out.println(count);
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

    }
}

