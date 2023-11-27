package use_case;

import app.api.RecipeImplementation;
import entity.Recipe;

import java.util.ArrayList;

public class SearchInteractor implements SearchInputBoundary {

    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchOutputBoundary searchPresenter) {
        this.searchPresenter = searchPresenter;
    }


    public void execute(SearchInputData searchInputData) {
        RecipeImplementation recipeImplementation = new RecipeImplementation();
        ArrayList<Recipe> results = recipeImplementation.getResults(searchInputData.getKeyword());
        if (!results.isEmpty()) {
            SearchOutputData searchOutputData = new SearchOutputData(results, false);
            searchPresenter.prepareSuccessView(searchOutputData); }
        else {
            searchPresenter.prepareFailView("No recipes found.");
        }
    }
}
