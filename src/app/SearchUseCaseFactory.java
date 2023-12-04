package app;

import entity.ShoppingListFactory;
import interface_adapter.*;
import interface_adapter.add_to_collection.AddCollectionViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.shopping_list.SLController;
import interface_adapter.shopping_list.SLPresenter;
import interface_adapter.shopping_list.SLViewModel;
import interface_adapter.show_collection.ShowCollectionController;
import interface_adapter.show_collection.ShowCollectionPresenter;
import interface_adapter.show_collection.ShowCollectionViewModel;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import use_case.shopping_list.SLDataAccessInterface;
import use_case.shopping_list.SLInputBoundary;
import use_case.shopping_list.SLInteractor;
import use_case.shopping_list.SLOutputBoundary;
import use_case.show_collection.ShowCollectionInteractor;
import use_case.show_collection.ShowCollectionOutputBoundary;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {

    private SearchUseCaseFactory() {}

    public static SearchView create(
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, AddCollectionViewModel addCollectionViewModel,
            ShowCollectionViewModel showCollectionViewModel, SLViewModel slViewModel, SLDataAccessInterface slDataAccesssObject) {

        try {
            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, addCollectionViewModel);
            ShowCollectionController showCollectionController = createShowCollectionUseCase(viewManagerModel, showCollectionViewModel);
            SLController shoppingListController = createShoppingListUseCase(viewManagerModel,slViewModel, addCollectionViewModel, slDataAccesssObject);

            return new SearchView(searchController, searchViewModel, showCollectionController, showCollectionViewModel, shoppingListController, slViewModel, addCollectionViewModel);
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

    private static SLController createShoppingListUseCase(ViewManagerModel viewManagerModel,
                                                         SLViewModel slViewmodel,
                                                         AddCollectionViewModel addCollectionViewModel,
                                                         SLDataAccessInterface slDataObject) throws IOException{
        SLOutputBoundary slOutputBoundary = new SLPresenter(viewManagerModel, slViewmodel);
        ShoppingListFactory shoppingListFactory = new ShoppingListFactory();

        SLInputBoundary slInteractor= new SLInteractor(slDataObject, slOutputBoundary, shoppingListFactory);

        return new SLController(slInteractor);
    }

}
