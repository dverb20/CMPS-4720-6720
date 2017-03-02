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


        Scanner scanner = new Scanner(new File(csvFile));
        //scanner.useDelimiter(",");
        while(scanner.hasNext()){
            String nextLine = scanner.nextLine();
            String[] arr=nextLine.split(",");
            //Size will vary based on csv file, this case 0-27
            data.add(count, arr);
            count++;
            //System.out.println(count);
        }
        scanner.close();

        int m = 0;
        int end = data.size();
        while(m < end){
            System.out.println(data.get(m)[1]);
            m = m+10;
        }
    }
}
