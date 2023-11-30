package view;

import entity.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShowCollectionView extends JFrame {

    private JList<String> recipeList;

    public ShowCollectionView() {
        super("Recipe Collection");

        JPanel panel = new JPanel(new BorderLayout());
        recipeList = new JList<>();

        JScrollPane scrollPane = new JScrollPane(recipeList);
        panel.add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        add(panel);
        setVisible(true);
    }

    public void displayRecipes(List<Recipe> recipes) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Recipe recipe : recipes) {
            listModel.addElement(recipe.getName());
            listModel.addElement(recipe.getCulture());
            listModel.addElement(String.valueOf(recipe.getCalories()));
            listModel.addElement("\n");
        }
        recipeList.setModel(listModel);
    }
}
