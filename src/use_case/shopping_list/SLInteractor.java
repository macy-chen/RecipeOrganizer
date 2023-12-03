package use_case.shopping_list;

import data_access.FileCollectionDataAccessObject;
import entity.RecipeCollection;
import entity.ShoppingList;
import entity.ShoppingListFactory;
import entity.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SLInteractor implements SLInputBoundary {

    final SLDataAccessInterface slDataAccessObject; //to output the shopping list file!

    final SLOutputBoundary slPresenter;

    final ShoppingListFactory slFactory;



    public SLInteractor(SLDataAccessInterface slDataAccessObject, SLOutputBoundary slPresenter, ShoppingListFactory slFactory){
        this.slDataAccessObject = slDataAccessObject;
        this.slPresenter = slPresenter;
        this.slFactory = slFactory;
    }

    @Override
    public void execute(SLInputData slInputData) throws IOException {
        try {
            FileCollectionDataAccessObject fileCollectionDataAccessObject = new FileCollectionDataAccessObject(slInputData.getCollectionPath());

            List<Recipe> recipes = fileCollectionDataAccessObject.getAll();
            ArrayList<Recipe> recipeArrayList = new ArrayList<>(recipes);
            RecipeCollection recipeCollection = new RecipeCollection(recipeArrayList);

            if (recipeCollection.getRecipes().isEmpty()) {
                slPresenter.prepareFailView("Recipe Collection is empty");
            } else {
                ShoppingList shoppingList = slFactory.create(recipeCollection);
                slDataAccessObject.save(shoppingList);

                SLOutputData slOutputData = new SLOutputData(shoppingList, false);
                slPresenter.prepareSuccessView(slOutputData);
            }
        } catch (IOException e){
            slPresenter.prepareFailView("Invalid Recipe Collection");
        }
    }
}
