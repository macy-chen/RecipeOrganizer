package use_case;

import app.api.RecipeImplementation;

public class SearchInteractor implements SearchInputBoundary {

    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchOutputBoundary searchPresenter) {
        this.searchPresenter = searchPresenter;
    }


    public void execute(SearchInputData searchInputData) {
        RecipeImplementation recipeImplementation = new RecipeImplementation();
        SearchOutputData searchOutputData = new SearchOutputData(recipeImplementation.getResults(searchInputData.getKeyword()), false);
        searchPresenter.prepareSuccessView(searchOutputData);
    }
}
