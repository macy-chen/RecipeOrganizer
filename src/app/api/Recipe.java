package app.api;

import java.util.ArrayList;

public class Recipe {

    String name;
    ArrayList<String> ingredients;
    String nutrients;
    Float calories;
    ArrayList<String> culture;
    String portion;

    String url;

    public Recipe(String name, ArrayList<String> ingredients, String nutrients, Float calories, ArrayList<String> culture, String portion, String url) {
        this.name = name;
        this.ingredients = ingredients;
        this.nutrients = nutrients;
        this.calories = calories;
        this.culture = culture;
        this.portion = portion;
        this.url = url;
    }
}
