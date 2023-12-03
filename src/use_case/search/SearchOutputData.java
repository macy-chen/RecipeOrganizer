package use_case.search;

import entity.Recipe;

import java.util.ArrayList;

public class SearchOutputData {

    private final ArrayList<Recipe> searchResults;
    private boolean useCaseFailed;

    public SearchOutputData(ArrayList<Recipe> searchResults, boolean useCaseFailed) {
        this.searchResults = searchResults;
    }

    public ArrayList<Recipe> getSearchResults() {
        return this.searchResults;
    }
}
