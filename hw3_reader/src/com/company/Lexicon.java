package com.company;
import java.util.*;
/**
 * Created by dannyverb on 3/6/17.
 */
public class Lexicon {

    private String header;
    private ArrayList<String> names;
    private ArrayList<Double> weights;

    public Lexicon(String h){
        header = h;
        names = new ArrayList<String>();
        weights = new ArrayList<Double>();
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
        return weights.get(index);
    }

    public void addFactor(String name, boolean success, double degree){
        if(this.nameExists(name) == false){
            names.add(name);
            if(success == true){
                weights.add(.01);
            }
            else{
                weights.add(-.01);
            }
        }
        else{//must have success gate
            int index = names.indexOf(name);
            double update;
            if(success == true){
                update = weights.get(index) + 0.01;
            }
            else{
                update = weights.get(index) - 0.01;
            }
            weights.set(index, update);
        }

    }

    public String toString(){
        String str = "";
        for(int i=0; i < this.names.size(); i++){
            str += this.names.get(i) + " : " + this.weights.get(i) + "\n";
        }
        str += this.header;
        str += " -- " + this.names.size();
        return str;
    }

}
