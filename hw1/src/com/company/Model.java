package com.company;
import java.io.*;


public class Model {
    private double[] weights;
    private double learning_rate;

    //initialize variables
    public Model(int num_weights, double rate){
        weights = [num_weights]
        learning_rate = rate;
    }

    public void changeValues(double[] examples){
        //function to change weight values
    }

    public int runSolve(double[] tests){
        if( tests.length != weights.length) return -99;
        double fX = weights[0];
        for(int i=1; i<tests.length; i++){
            fX += weights[i]*tests[i];
        }
        int answer = (int) (fX + 0.5);
        return answer;
    }

}