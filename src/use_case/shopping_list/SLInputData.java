package use_case.shopping_list;
import entity.RecipeCollection;

public class SLInputData {

    final private RecipeCollection recipeCollection;

    public SLInputData(RecipeCollection recipeCollection){
        this.recipeCollection = recipeCollection;
    }


    public RecipeCollection getRecipeCollection() {
        return recipeCollection;
    }
}
