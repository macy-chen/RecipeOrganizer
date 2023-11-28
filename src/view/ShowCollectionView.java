package view;

import entity.Recipe;
import interface_adapter.ShowCollectionController;
import interface_adapter.ShowCollectionViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShowCollectionView extends JFrame {

    private JList<String> recipeList;
    private ShowCollectionViewModel showCollectionViewModel;

    public ShowCollectionView() {
        // Set the title of the window
        super("Recipe Collection");

        // Create a panel to hold the components
        JPanel panel = new JPanel(new BorderLayout());

        // Create a DietViewModel to manage the data
        showCollectionViewModel = new ShowCollectionViewModel();
        recipeList = new JList<>(showCollectionViewModel.getListModel());

        JScrollPane scrollPane = new JScrollPane(recipeList);

        // Add the JScrollPane to the panel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Set the default close operation and size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Add the panel to the frame
        add(panel);

        // Set the frame to be visible
        setVisible(true);

    }
}
