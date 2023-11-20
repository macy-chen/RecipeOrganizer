package use_case.shopping_list;

import entity.RecipeCollection;
import entity.ShoppingList;
import entity.ShoppingListFactory;

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
    public void execute(SLInputData slInputData) {
        RecipeCollection recipeCollection = slInputData.getRecipeCollection();
        if (recipeCollection == null){
            slPresenter.prepareFailView("Recipe Collection is empty");
        }

        else{
            ShoppingList shoppingList = slFactory.create(slInputData.getRecipeCollection());
            slDataAccessObject.save(shoppingList);

            SLOutputData slOutputData =  new SLOutputData(shoppingList.toString(), false);
            slPresenter.prepareSuccessView(slOutputData);
        }
    }
}
