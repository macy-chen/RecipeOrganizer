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
        for (int i = 0; i < selectedRecipes.size(); i++) {
            if (collectionDataAccessObject.existsByName(selectedRecipes.get(i).getName())) {
                String failMessage = String.format("%s already exists in collection.", selectedRecipes.get(i).getName());
                addCollectionPresenter.prepareFailView(failMessage);
            } else {
                collectionDataAccessObject.save(selectedRecipes.get(i));
                List<Recipe> collection = collectionDataAccessObject.getAll();
                AddCollectionOutputData addCollectionOutputData = new AddCollectionOutputData(collection, false);
                addCollectionPresenter.prepareSuccessView(addCollectionOutputData);
            }
            selectedRecipes.remove(selectedRecipes.get(i));
        }
    }
}
