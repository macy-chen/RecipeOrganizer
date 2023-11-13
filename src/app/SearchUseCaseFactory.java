package app;

import interface_adapter.*;
import use_case.SearchInputBoundary;
import use_case.SearchInteractor;
import use_case.SearchOutputBoundary;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {

    private SearchUseCaseFactory() {}

    public static SearchView create(
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel) {

        try {
            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel);
            return new SearchView(searchController, searchViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel, viewManagerModel);


        SearchInputBoundary searchInteractor = new SearchInteractor(searchOutputBoundary);

        return new SearchController(searchInteractor);
    }

    private static SearchController createClearUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel) throws IOException {
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel, viewManagerModel);

        SearchInputBoundary searchInteractor = new SearchInteractor(searchOutputBoundary);

        return new SearchController(searchInteractor);
    }
}
