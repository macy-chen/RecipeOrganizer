package interface_adapter;

import entity.Recipe;
import use_case.ShowCollectionInteractor;

import javax.swing.*;
import java.util.List;

public class ShowCollectionViewModel {
    private DefaultListModel<String> listModel;
    private ShowCollectionInteractor showCollectionInteractor;

    public ShowCollectionViewModel() {
        this.showCollectionInteractor = showCollectionInteractor;
        this.listModel = new DefaultListModel<>();
        updateListModelWithRecipes();
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public void updateListModelWithRecipes() {
        // Ensure showCollectionInteractor is not null
        if (showCollectionInteractor != null) {
            List<Recipe> recipes = showCollectionInteractor.execute();
            for (Recipe recipe : recipes) {
                listModel.addElement(recipe.getName());
                listModel.addElement(recipe.getCulture());
                listModel.addElement(String.valueOf(recipe.getCalories()));
                listModel.addElement("\n");
            }
        } else {
            // Handle the case when showCollectionInteractor is null
            System.out.println("showCollectionInteractor is null. Unable to update list model.");
        }
    }
}
