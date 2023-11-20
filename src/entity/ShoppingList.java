package entity;

import java.util.ArrayList;

public class ShoppingList {
    private ArrayList<Ingredient> ingredients;

    public ShoppingList(){

    }

    public ShoppingList(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients; //need to check?
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    //add try catch for if ingredients is empty
    public void addIngredient(Ingredient ingredient){
        for(int i=0; i < ingredients.size(); i++){ //checks overlap in existing ingredients (overlap in name & measurement unit) --> add the quantities under one Ingredient object
            if (ingredient.getName().equalsIgnoreCase(ingredients.get(i).getName()) && ingredient.getMeasurement().equalsIgnoreCase(ingredients.get(i).getMeasurement()) ){
                ingredients.get(i).setAmount(ingredients.get(i).getAmount() + ingredient.getAmount());
            }
            else{ //ingredient not in arrayList OR different units --> just add as new element in arraylist
                ingredients.add(ingredient);
            }
        }
    }

    public String toString(){
        String s = null;
        for (Ingredient i: ingredients){
            s.concat(i.toString());
        }
        return s;
    }


}
