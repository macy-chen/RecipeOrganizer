package entity;

import java.util.ArrayList;

public class ShoppingListFactory {
    private ShoppingList shoppingList;

    public ShoppingListFactory() {

    }

    public ShoppingList create(RecipeCollection recipeCollection){
        ArrayList<Recipe> recipes = recipeCollection.getRecipes();
        for (Recipe recipe : recipes) { //goes through each recipe in collection
            for (int i = 0; i < recipe.getIngredients().size(); i++) {
                shoppingList.addIngredient(recipe.getIngredients().get(i));
            }
        }
        return shoppingList;
    }

}
