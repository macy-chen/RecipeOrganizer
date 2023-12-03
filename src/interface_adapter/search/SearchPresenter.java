package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_collection.AddCollectionState;
import interface_adapter.add_to_collection.AddCollectionViewModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;
    private final AddCollectionViewModel addCollectionViewModel;

    public SearchPresenter(SearchViewModel viewModel, ViewManagerModel viewManagerModel, AddCollectionViewModel addCollectionViewModel) {
        this.searchViewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.addCollectionViewModel = addCollectionViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData results) {
        AddCollectionState addCollectionState = addCollectionViewModel.getState();
        addCollectionState.setRecipeResults(results.getSearchResults());
        this.addCollectionViewModel.setState(addCollectionState);
        addCollectionViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(addCollectionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        System.out.println(results.getSearchResults());
    }

    @Override
    public void prepareFailView(String error) {
        SearchState searchState = searchViewModel.getState();
        searchState.setSearchError(error);
        searchViewModel.firePropertyChanged();
    }
}
