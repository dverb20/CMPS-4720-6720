package com.company;
import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) {

        String csvFile = "/Users/dannyverb/Desktop/iris.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] feature = line.split(cvsSplitBy);

                System.out.println("number [code= " + feature[0] + " , name=" + feature[2] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}

