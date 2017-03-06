package com.company;
import java.io.*;


public class Model {
    private double[] weights;
    private double learning_rate;

    //initialize variables
    public Model(int num_weights, double rate){
        //weights = new double[num_weights];
        double[] fill = {0.0,0.0,0.0,0.0};
        weights = fill;
        learning_rate = rate;
    }

    public void changeValues(double[] examples, int total, int expected){
        //function to change weight values
        int sign = expected - total;
        if(sign > 0) sign = 1;
        if(sign < 0) sign = -1;
        for(int i=0; i < weights.length; i++){
            // missing a y, update only if y != y'
            //get y by finding the sign of the dot(w .. x), if target is different from what I have
            weights[i] = (weights[i] + (learning_rate * examples[i] * sign));
        }
    }

    public int runSolve(double[] examples){
        //if( tests.length != weights.length-1) return -99;
        double fX = weights[0];
        for(int i=0; i<weights.length; i++){
            fX += weights[i]*examples[i];
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