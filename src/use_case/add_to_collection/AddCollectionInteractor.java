package use_case.add_to_collection;

import entity.Recipe;
import java.util.*;
import java.util.List;

public class AddCollectionInteractor implements AddCollectionInputBoundary {
    final AddCollectionCollectionDataAccessInterface collectionDataAccessObject;
    final AddCollectionOutputBoundary addCollectionPresenter;
    private Recipe selectedRecipe;

    public AddCollectionInteractor(AddCollectionCollectionDataAccessInterface collectionDataAccessObject,
                                   AddCollectionOutputBoundary addCollectionPresenter) {
        this.collectionDataAccessObject = collectionDataAccessObject;
        this.addCollectionPresenter = addCollectionPresenter;

        selectedRecipe = new Recipe();
    }

    @Override
    public void execute(AddCollectionInputData addCollectionInputData) {
        int selectedBox = addCollectionInputData.getSelectedBox();
        selectedRecipe = addCollectionInputData.getRecipeResults().get(selectedBox);

        if (collectionDataAccessObject.existsByName(selectedRecipe.getName())) {
            String failMessage = String.format("%s already exists in collection.", selectedRecipe.getName());
            addCollectionPresenter.prepareFailView(failMessage);
        } else {
            collectionDataAccessObject.save(selectedRecipe);
            List<Recipe> collection = collectionDataAccessObject.getAll();
            AddCollectionOutputData addCollectionOutputData = new AddCollectionOutputData(collection, false);
            addCollectionPresenter.prepareSuccessView(addCollectionOutputData);
        }
    }
}
