package entity;

import java.util.ArrayList;

public class Ingredient {

    private String name;
    private Float amount;
    private String measurement; // the unit of measurement for amount: ex. cups, grams, mL
    private Float calories;
    private ArrayList<Nutrient> nutrients;
    private String category; //fv, grain, protein, condiment, liquid


    public Ingredient(){

    }

    public String getName() {
        return name;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public void setNutrients(ArrayList<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMeasurement() {
        return measurement;
    }

    public String getCategory() {
        return category;
    }

    public String toString(){ //formats each ingredient as a new line
        return (String.format(this.name, "\t", amount, "\t", measurement, "\n")); //name, amount, measurement
    }
}
