package entity;

import java.util.*;

public class RecipeCollection {
    private ArrayList<Recipe> recipes;

    public RecipeCollection(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public RecipeCollection() {

    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
}
