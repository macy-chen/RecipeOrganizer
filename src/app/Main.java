package app;

import data_access.FileCollectionDataAccessObject;
import interface_adapter.SearchViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_collection.AddCollectionViewModel;
import use_case.add_to_collection.AddCollectionCollectionDataAccessInterface;
import view.ResultsView;
import view.SearchView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame application = new JFrame("Search Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SearchViewModel searchViewModel = new SearchViewModel();

        AddCollectionViewModel addCollectionViewModel = new AddCollectionViewModel();

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, addCollectionViewModel);
        views.add(searchView, searchView.viewName);

        AddCollectionCollectionDataAccessInterface addCollectionCollectionDataAccessInterface = new FileCollectionDataAccessObject("./recipe");

        ResultsView resultsView = AddCollectionUseCaseFactory.create(viewManagerModel, addCollectionViewModel, addCollectionCollectionDataAccessInterface);
        views.add(resultsView, resultsView.viewName);

        viewManagerModel.setActiveView(searchView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
