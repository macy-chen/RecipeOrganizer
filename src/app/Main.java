package app;

import interface_adapter.SearchViewModel;
import interface_adapter.ViewManagerModel;
import view.SearchView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Search Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SearchViewModel searchViewModel = new SearchViewModel();

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel);
        views.add(searchView, searchView.viewName);

        application.pack();
        application.setVisible(true);
    }
}
