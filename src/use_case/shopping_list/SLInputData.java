package use_case.shopping_list;
import entity.RecipeCollection;

public class SLInputData {

    //final private RecipeCollection recipeCollection;
    final private String collectionPath;

    public SLInputData(String path){
        this.collectionPath = path;
    }


    /*public RecipeCollection getRecipeCollection() {
        return recipeCollection;
    }*/

    public String getCollectionPath() {
        return collectionPath;
    }
}
