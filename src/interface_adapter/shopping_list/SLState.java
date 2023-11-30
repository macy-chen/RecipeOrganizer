package interface_adapter.shopping_list;

import entity.RecipeCollection;
import entity.ShoppingList;

public class SLState {
    private ShoppingList shoppingList;
    private String shoppingListError = null;

    private RecipeCollection recipeCollection;


    public SLState(SLState copy){
        shoppingList = copy.shoppingList;
        shoppingListError = copy.shoppingListError;
        recipeCollection = copy.recipeCollection;
    }

    public SLState(){
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public String getShoppingListError() {
        return shoppingListError;
    }

    public void setShoppingListError(String shoppingListError) {
        this.shoppingListError = shoppingListError;
    }

    public RecipeCollection getRecipeCollection() {
        return recipeCollection;
    }

    public void setRecipeCollection(RecipeCollection recipeCollection) {
        this.recipeCollection = recipeCollection;
    }

//    @Override
//    public String toString() {
//        return "SLState{" +
//                "shoppingList='" + shoppingList + '\'' +
//                '}';
//    }
}

