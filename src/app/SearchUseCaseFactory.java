package app;

import interface_adapter.*;
import interface_adapter.add_to_collection.AddCollectionViewModel;
import use_case.SearchInputBoundary;
import use_case.SearchInteractor;
import use_case.SearchOutputBoundary;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {

    private SearchUseCaseFactory() {}

    public static SearchView create(
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, AddCollectionViewModel addCollectionViewModel) {

        try {
            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, addCollectionViewModel);
            return new SearchView(searchController, searchViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed.");
        }

        return null;
    }

    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, AddCollectionViewModel addCollectionViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel, viewManagerModel, addCollectionViewModel);


        SearchInputBoundary searchInteractor = new SearchInteractor(searchOutputBoundary);

        return new SearchController(searchInteractor);
    }

}
