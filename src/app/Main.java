package app;

import data_access.FileCollectionDataAccessObject;
import interface_adapter.add_to_collection.AddCollectionViewModel;
import interface_adapter.ViewManagerModel;
import view.ResultsView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Search Results");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        AddCollectionViewModel addCollectionViewModel = new AddCollectionViewModel();

        FileCollectionDataAccessObject collectionDataAccessObject;
        try {
            collectionDataAccessObject = new FileCollectionDataAccessObject("./recipeCollection.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ResultsView resultsView = AddCollectionUseCaseFactory.create(viewManagerModel, addCollectionViewModel, collectionDataAccessObject);
        views.add(resultsView, resultsView.viewName);

        application.pack();
        application.setVisible(true);
    }
}
