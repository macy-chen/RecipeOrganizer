package use_case.add_to_collection;

import entity.Recipe;
import java.util.*;
import java.util.List;

public class AddCollectionInteractor implements AddCollectionInputBoundary {
    final AddCollectionCollectionDataAccessInterface collectionDataAccessObject;
    final AddCollectionOutputBoundary addCollectionPresenter;
    final private List<Recipe> selectedRecipes;

    public AddCollectionInteractor(AddCollectionCollectionDataAccessInterface collectionDataAccessObject,
                                   AddCollectionOutputBoundary addCollectionPresenter) {
        this.collectionDataAccessObject = collectionDataAccessObject;
        this.addCollectionPresenter = addCollectionPresenter;

        selectedRecipes = new ArrayList<>();
    }

    @Override
    public void execute(AddCollectionInputData addCollectionInputData) {
        for (int num : addCollectionInputData.getSelectedBoxes()) {
            selectedRecipes.add(addCollectionInputData.getRecipeResults().get(num));
        }
        for (Recipe recipe : selectedRecipes) {
            if (collectionDataAccessObject.existsByName(recipe.getName())) {
                addCollectionPresenter.prepareFailView("Recipe already exists in collection.");
            } else {
                collectionDataAccessObject.save(recipe);
                List<Recipe> collection = collectionDataAccessObject.getAll();
                AddCollectionOutputData addCollectionOutputData = new AddCollectionOutputData(recipe.getName(), collection, false);
                addCollectionPresenter.prepareSuccessView(addCollectionOutputData);
            }
        }
    }
}
