package interface_adapter;

import use_case.SearchInputBoundary;
import use_case.SearchInputData;

public class SearchController {

    final SearchInputBoundary searchInteractor;

    public SearchController(SearchInputBoundary searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    public void execute(String keyword) {
        SearchInputData searchInputData = new SearchInputData(keyword);
        searchInteractor.execute(searchInputData);
    }


}
