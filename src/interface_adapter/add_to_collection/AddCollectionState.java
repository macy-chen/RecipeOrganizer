package interface_adapter.add_to_collection;

import entity.Recipe;
import java.util.List;

public class AddCollectionState {
    private String addCollectionError = null;
    private List<Recipe> recipeResults;

    public AddCollectionState(AddCollectionState copy) {
        addCollectionError = copy.addCollectionError;
        recipeResults = copy.recipeResults;
    }

    public AddCollectionState() {
    }

    public String getAddCollectionError() {
        return addCollectionError;
    }

    public void setCollectionError(String error) {
        this.addCollectionError = error;
    }

    public List<Recipe> getRecipeResults() {
        return recipeResults;
    }
}
