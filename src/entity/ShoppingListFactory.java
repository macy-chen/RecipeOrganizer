package entity;

import java.util.ArrayList;

public class ShoppingListFactory {
    //private ShoppingList shoppingList = new ShoppingList();

    public ShoppingListFactory() {

    }

    public ShoppingList create(RecipeCollection recipeCollection){
        ShoppingList shoppingList = new ShoppingList();
        ArrayList<Recipe> recipes = recipeCollection.getRecipes();
        for (Recipe recipe : recipes) { //goes through each recipe in collection
            for (Ingredient i: recipe.getIngredients()){
                shoppingList.addIngredient(i);
            }
        }
        return shoppingList;
    }

}
