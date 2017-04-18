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
    private ArrayList<Integer> updates;
    private double learningRate;

    public Lexicon(String h){
        header = h;
        names = new ArrayList<String>();
        weights = new ArrayList<Double>();
        appearances = new ArrayList<Integer>();
        updates = new ArrayList<Integer>();
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

    public int getUpdates(String name){
        int index = names.indexOf(name);
        return updates.get(index);
    }

    public void addFactor(String name, double margin){
        names.add(name);
        appearances.add(1);
        weights.add(0.0);
        updates.add(0);
    }

    public void update(String name, double margin, double budget){
        double weight = this.getWeight(name);
        int index = names.indexOf(name);
        updates.set(index, (updates.get(index)+1));
        int apps = this.getUpdates(name);
        double den = Math.log(20);
        double sign = 0;
        //if(budget > 1000000000){
            //sign = expected - margin + .3;
        //}
        //else {
        sign = margin - weight;
        double dif = Math.abs(sign);
        double update;
        //}
//        if(sign > 0) {sign = 1.0;}
//        if(sign < 0){ sign = -1.0; }
        double added = (Math.log(margin*10)/den);
        //System.out.println(weight + " : " + margin + " : " + (Math.log(margin*10)/den));
        update = ((weight*(apps-1))+added)/(apps);
        //double update = weight + (learningRate*sign*dif);
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
