package entity;

import java.util.ArrayList;

public class Ingredient {

    private String name;
    private Float amount; //cups, grams, mL (need to find a way to convert...)
    private Float calories;
    private ArrayList<Nutrient> nutrients;
    private String category; //fv, grain, protein, condiment, liquid


    public Ingredient(){

    }
}
