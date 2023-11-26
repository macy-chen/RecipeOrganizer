package interface_adapter.add_to_collection;

import entity.Recipe;
import use_case.add_to_collection.AddCollectionInputBoundary;
import use_case.add_to_collection.AddCollectionInputData;

import java.util.List;

public class AddCollectionController {
    final AddCollectionInputBoundary addCollectionUseCaseInteractor;

    public AddCollectionController(AddCollectionInputBoundary addCollectionUseCaseInteractor) {
        this.addCollectionUseCaseInteractor = addCollectionUseCaseInteractor;
    }

    public void execute(List<Integer> selectedBoxes, List<Recipe> recipeResults) {
        AddCollectionInputData addCollectionInputData = new AddCollectionInputData(selectedBoxes, recipeResults);

        addCollectionUseCaseInteractor.execute(addCollectionInputData);
    }
}
