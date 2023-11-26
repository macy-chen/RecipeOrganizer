package entity;
import java.util.ArrayList;



public class Recipe {
    String name;
    ArrayList<Ingredient> ingredients;
    String nutrients;
    Float calories;
    ArrayList<String> culture;
    String portion;

    String url;

    public Recipe(String name, ArrayList<Ingredient> ingredients, String nutrients, Float calories, ArrayList<String> culture, String portion, String url) {
        this.name = name;
        this.ingredients = ingredients;
        this.nutrients = nutrients;
        this.calories = calories;
        this.culture = culture;
        this.portion = portion;
        this.url = url;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Recipe(){
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
