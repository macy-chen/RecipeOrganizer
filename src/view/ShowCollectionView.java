package view;

import entity.Recipe;
import interface_adapter.show_collection.ShowCollectionController;
import interface_adapter.show_collection.ShowCollectionViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowCollectionView extends JFrame {
    private JList<String> recipeList;
    private DefaultListModel<String> listModel;
    public final String viewName = "showCollection";
    private final ShowCollectionViewModel showCollectionViewModel;
    private final ShowCollectionController showCollectionController;

    public ShowCollectionView(ShowCollectionViewModel showCollectionViewModel,
                              ShowCollectionController showCollectionController) {
        this.showCollectionViewModel = showCollectionViewModel;
        this.showCollectionController = showCollectionController;


        // Create a panel to hold the components
        JPanel panel = new JPanel(new BorderLayout());

        // Create a DefaultListModel to store the recipe names
        listModel = new DefaultListModel<>();

        // Create a JList with the DefaultListModel
        recipeList = new JList<>(listModel);

        // Create some sample recipes (you can replace this with your actual data)
        List<Recipe> recipes = showCollectionController.execute();

        for (Recipe recipe: recipes) {
            System.out.println(recipe.getName());
        }

        // Add recipe names to the list model
        for (Recipe recipe: recipes) {
            listModel.addElement(recipe.getName());
            listModel.addElement(recipe.getCulture());
            listModel.addElement(String.valueOf(recipe.getCalories()));
            listModel.addElement("\n");
        }

        // Create a JScrollPane to hold the JList
        JScrollPane scrollPane = new JScrollPane(recipeList);

        // Add the JScrollPane to the panel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Set the default close operation and size of the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        // Add the panel to the frame
        add(panel);

        // Set the frame to be visible
        setVisible(true);
    }

    void setRecipeList(List<Recipe> recipeList) {
        JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
        recipePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (Recipe recipe: recipeList) {
            JLabel recipeLabel = new JLabel("<html>" + recipe.getName() + "<br>" +
                    "Calories: " + recipe.getCalories() + "<html>" + recipe.getCulture());
            recipePanel.add(recipeLabel);
        }

        // Remove the old recipePanel and add the new one
        this.remove(1);
        this.add(recipePanel, 1);
        this.revalidate();
        this.repaint();
    }
}
