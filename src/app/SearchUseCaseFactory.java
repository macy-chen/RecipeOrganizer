package app;

import interface_adapter.*;
import interface_adapter.add_to_collection.AddCollectionViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.show_collection.ShowCollectionController;
import interface_adapter.show_collection.ShowCollectionPresenter;
import interface_adapter.show_collection.ShowCollectionViewModel;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import use_case.show_collection.ShowCollectionInteractor;
import use_case.show_collection.ShowCollectionOutputBoundary;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {

    private SearchUseCaseFactory() {}

    public static SearchView create(
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, AddCollectionViewModel addCollectionViewModel,
            ShowCollectionViewModel showCollectionViewModel) {

        try {
            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, addCollectionViewModel);
            ShowCollectionController showCollectionController = createShowCollectionUseCase(viewManagerModel, showCollectionViewModel);
            return new SearchView(searchController, searchViewModel, showCollectionController, showCollectionViewModel);
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

    private static ShowCollectionController createShowCollectionUseCase(ViewManagerModel viewManagerModel, ShowCollectionViewModel showCollectionViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        ShowCollectionOutputBoundary showCollectionOutputBoundary = new ShowCollectionPresenter(showCollectionViewModel, viewManagerModel);


        ShowCollectionInteractor showCollectionInteractor = new ShowCollectionInteractor(showCollectionOutputBoundary);

        return new ShowCollectionController(showCollectionInteractor);
    }

}
