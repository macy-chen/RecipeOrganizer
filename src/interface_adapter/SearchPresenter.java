package interface_adapter;

import use_case.SearchOutputBoundary;
import use_case.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;
    //private final ResultsViewModel resultsViewModel;

    public SearchPresenter(SearchViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.searchViewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        //this.resultsViewModel = resultsViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData results) {
//        ResultsState resultsState = resultsViewModel.getState();
//        resultsState.setResults(results.getSearchResults());
//        this.resultsViewModel.setState(resultsState);
//        resultsViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(resultsViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
        System.out.println(results.getSearchResults());
    }

    @Override
    public void prepareFailView(String error) {

    }
}
