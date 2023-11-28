package use_case;

import entity.Recipe;

import java.util.List;

public class ShowCollectionInteractor implements ShowCollectionInputBoundary {

    final ShowCollectionOutputBoundary ShowCollectionPresenter;
    final ShowCollectionCollectionDataAccessInterface collectionDataAccessObject;
    List<Recipe> collections = null;

    public ShowCollectionInteractor(ShowCollectionOutputBoundary showCollectionPresenter, ShowCollectionCollectionDataAccessInterface collectionDataAccessObject) {
        this.ShowCollectionPresenter = showCollectionPresenter;
        this.collectionDataAccessObject = collectionDataAccessObject;
    }


    public static void execute(ShowCollectionInputData showCollectionInputData) {
        collections = collectionDataAccessObject.load(showCollectionInputData);
        for (Recipe collection: collections) {
            ShowCollectionOutputData showCollectionOutputData = new ShowCollectionOutputData(collection.getName(), collection.getCalories(), collection.getCulture());
        }

    }
}
