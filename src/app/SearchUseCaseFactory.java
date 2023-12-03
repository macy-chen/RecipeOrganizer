package app;

import interface_adapter.*;
import interface_adapter.add_to_collection.AddCollectionViewModel;
import use_case.*;
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
