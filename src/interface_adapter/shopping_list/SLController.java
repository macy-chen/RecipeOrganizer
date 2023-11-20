package interface_adapter.shopping_list;

import entity.RecipeCollection;
import use_case.shopping_list.SLInputBoundary;
import use_case.shopping_list.SLInputData;
import use_case.shopping_list.SLOutputBoundary;

public class SLController {
    final SLInputBoundary slInputUseCaseInteractor;

    public SLController(SLInputBoundary slInputUseCaseInteractor){
        this.slInputUseCaseInteractor = slInputUseCaseInteractor;
    }

    public void execute(RecipeCollection recipeCollection) {
        SLInputData slInputData = new SLInputData(recipeCollection);
        slInputUseCaseInteractor.execute(slInputData);
    }
}
