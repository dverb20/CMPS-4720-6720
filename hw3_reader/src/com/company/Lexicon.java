package com.company;
import java.util.*;
/**
 * Created by dannyverb on 3/6/17.
 */
public class Lexicon {

    private String header;
    private ArrayList<String> names;
    private ArrayList<Double> weights;
    private ArrayList<Integer> appearances;
    private double learningRate;

    public Lexicon(String h){
        header = h;
        names = new ArrayList<String>();
        weights = new ArrayList<Double>();
        appearances = new ArrayList<Integer>();
        learningRate = 0.08;
    }

    public boolean nameExists(String name){
        if(names.indexOf(name) == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public double getWeight(String name){
        int index = names.indexOf(name);
        if(index == -1){
            return 0.5;
        }
        return weights.get(index);
    }

    public int getAppearances(String name){
        int index = names.indexOf(name);
        if(index == -1){
            return 1;
        }
        return appearances.get(index);
    }

    public void addAppearance(String name){
        int index = names.indexOf(name);
        appearances.set(index, (appearances.get(index)+1));
    }

    public void addFactor(String name, double margin){
        names.add(name);
        appearances.add(1);
        weights.add(learningRate);

    }

    public void update(String name, double margin, double budget){
        double weight = this.getWeight(name);
        int index = names.indexOf(name);
        double sign = 0;
        //if(budget > 1000000000){
            //sign = expected - margin + .3;
        //}
        //else {
        sign = margin - weight;
        double dif = Math.abs(sign);
        //}
        if(sign > 0) {sign = 1.0;}
        if(sign < 0){ sign = -1.0; }
        double update = weight + (learningRate*sign*dif);
        weights.set(index, update);
    }

    public String toString(){
        String str = "";
        for(int i=0; i < this.names.size(); i++){
            str += this.names.get(i) + " : " + this.weights.get(i) + ", " + this.appearances.get(i) + "\n";
        }
        str += this.header;
        str += " -- " + this.names.size();
        return str;
    }

}
