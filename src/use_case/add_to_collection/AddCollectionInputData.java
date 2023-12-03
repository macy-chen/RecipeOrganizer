package use_case.add_to_collection;

import entity.Recipe;

import javax.swing.*;
import java.util.*;

public class AddCollectionInputData {

    final private int selectedBox;
    final private List<Recipe> recipeResults;
    public AddCollectionInputData(int selectedBox,
                                  List<Recipe> recipeResults) {
        this.selectedBox = selectedBox;
        this.recipeResults = recipeResults;
    }

    int getSelectedBox() {
        return selectedBox;
    }
    List<Recipe> getRecipeResults() {
        return recipeResults;
    }
}
