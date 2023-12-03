package interface_adapter.show_collection;

import entity.Recipe;

import java.util.List;

public class ShowCollectionState {
    private List<Recipe> recipes;
    private String Error = null;

    public ShowCollectionState(ShowCollectionState copy) {
        this.recipes = copy.recipes;
        this.Error = copy.Error;
    }

    public ShowCollectionState () {}

    public void setCollection(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }

    public List<Recipe> getRecipeList() {return recipes;}
}
