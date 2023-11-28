package interface_adapter;

import entity.Recipe;
import use_case.ShowCollectionInteractor;
import use_case.ShowCollectionOutputData;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ShowCollectionViewModel {
    private DefaultListModel<String> listModel;

    public ShowCollectionViewModel() {
        // Create a DefaultListModel to store the recipe names
        listModel = new DefaultListModel<>();

        // Create some sample recipes (you can replace this with your actual data)
        ArrayList<Recipe> recipes = ShowCollectionInteractor.execute();

        // Add recipe names to the list model
        for (Recipe recipe : recipes) {
            listModel.addElement(recipe.getName());
            listModel.addElement(recipe.getCulture());
            listModel.addElement(String.valueOf(recipe.getCalories()));
            listModel.addElement("\n");
        }
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }
}
