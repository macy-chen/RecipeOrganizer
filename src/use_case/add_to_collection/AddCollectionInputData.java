package use_case.add_to_collection;

import entity.Recipe;

import javax.swing.*;
import java.util.*;

public class AddCollectionInputData {

    final private List<Integer> selectedBoxes;
    final private List<Recipe> recipeResults;
    public AddCollectionInputData(List<Integer> selectedBoxes,
                                  List<Recipe> recipeResults) {
        this.selectedBoxes = selectedBoxes;
        this.recipeResults = recipeResults;
    }

    List<Integer> getSelectedBoxes() {
        return selectedBoxes;
    }
    List<Recipe> getRecipeResults() {
        return recipeResults;
    }
}
