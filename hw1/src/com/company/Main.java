package com.company;
import java.util.*;
import java.io.*;

public class Machine_Learning_hw1 {

    public static void main(String[] args) {

        String csvFile = "/Users/dverb/desktop/iris.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] feature = line.split(cvsSplitBy);

                System.out.println("number [code= " + feature[0] + " , name=" + country[2] + "]");

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

public class Model {
    private double w0;
    private double w1;
    private double w2;
    private double w3;
    private double w4;

    //initialize variables
    public Model(double a, double b, double c, double d, double e){
        w0 = a;
        w1 = b;
        w2 = c;
        w3 = d;
        w4 = e;
    }

    public void change0(double newVar){
        w0 = newVar;
    }

    public void change1(double newVar){
        w1 = newVar;
    }

    public void change2(double newVar){
        w2 = newVar;
    }

    public void change3(double newVar){
        w3 = newVar;
    }

    public void change4(double newVar){
        w4 = newVar;
    }

    public int runSolve(double x, double y, double z, double w){
        double fX = w0 + x*w1 + y*w2 + z*w3 + w*w4;
        int answer = (int) (fX + 0.5);
        return answer;
    }

    public double get0(){
        return w0;
    }

    public double get1(){
        return w1;
    }

    public double get2(){
        return w2;
    }

    public double get3(){
        return w3;
    }

    public double get4(){
        return w4;
    }

}
