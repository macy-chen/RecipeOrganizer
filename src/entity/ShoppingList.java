package entity;

import java.util.ArrayList;

public class ShoppingList {
    private ArrayList<Ingredient> ingredients;

    public ShoppingList(){
        ingredients = new ArrayList<Ingredient>();
    }

    public ShoppingList(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients; //need to check?
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    //add try catch for if ingredients is empty
    public void addIngredient(Ingredient newIngredient){
        //check overlap in existing ingredients (overlap in name & measurement unit) --> add the quantities under one Ingredient object
        for (Ingredient existingIngredient : ingredients) {
            if (newIngredient.getName().equalsIgnoreCase(existingIngredient.getName()) && newIngredient.getMeasurement().equalsIgnoreCase(existingIngredient.getMeasurement())) {
                existingIngredient.setAmount(existingIngredient.getAmount() + newIngredient.getAmount());
                return;
            }
            //else{
                //ingredients.add(newIngredient);
            //}

        }
        //ingredient not in arrayList OR different units --> just add as new element in arraylist
        ingredients.add(newIngredient);
    }

    public String ingredientstoString(){
        String s = "";
        String header = String.format("%-15s %-15s %s", "Ingredient", "Amount", "Unit");
        s = s.concat(header + "\n");
        s = s.concat("---------------------------------------------" + "\n");

        for (Ingredient i: ingredients){
            s = s.concat(i.toString());
        }
        s = s.replace("[", "");
        s = s.replace("]", "");
        s = s.replace(",", ""); //make sure no "," in API return values...
        return s;
    }


}
