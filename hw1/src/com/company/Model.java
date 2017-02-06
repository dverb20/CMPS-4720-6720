package com.company;
import java.io.*;


public class Model {
    private double[] weights;
    private double learning_rate;

    //initialize variables
    public Model(int num_weights, double rate){
        weights = new double[num_weights];
        learning_rate = rate;
    }

    public void changeValues(double[] examples, int sign){
        //function to change weight values
        for(int i=1; i<weights.length; i++){
            // missing a y, update only if y != y'
            //get y by finding the sign of the dot(w .. x)
            weights[i] = (weights[i] + learning_rate * examples[i-1] * sign);
        }
    }

    public int runSolve(double[] tests){
        //if( tests.length != weights.length-1) return -99;
        double fX = weights[0];
        for(int i=1; i<weights.length; i++){
            fX += weights[i]*tests[i-1];
        }
        int answer = (int) (fX + 0.5);
        return answer;
    }

    public String toString(){
        String mod = "";
        int count = 0;
        for (double weight : weights) {
            mod += "w" + count +": " + weight +", ";
            count++;
        }
        return mod;
    }

}