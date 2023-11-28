package interface_adapter;

import entity.RecipeCollection;

public class ShowCollectionState {
    private RecipeCollection recipeCollection;
    private String Error = null;

    public ShowCollectionState(ShowCollectionState copy) {
        this.recipeCollection = copy.recipeCollection;
        this.Error = copy.Error;
    }

    public ShowCollectionState () {}

    public RecipeCollection getRecipeCollection() {
        return recipeCollection;
    }

    public void setCollection(RecipeCollection recipeCollection) {
        this.recipeCollection = recipeCollection;
    }

    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }

    @Override
    public String toString() {
        return "SignupState{" +
                "recipecollection='" + recipeCollection + '\'' +
                '}';
    }
}
