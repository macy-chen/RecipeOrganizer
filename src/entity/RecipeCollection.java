package entity;

import java.util.*;

public class RecipeCollection {
    private ArrayList<Recipe> recipes;

    RecipeCollection(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public RecipeCollection() {
        recipes = new ArrayList<Recipe>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
    public ArrayList<Recipe> getRecipes() {return this.recipes;}

}
