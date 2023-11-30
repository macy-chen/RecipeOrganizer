package use_case;

import data_access.FileCollectionDataAccessObject;
import entity.Recipe;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ShowCollectionInteractor implements ShowCollectionInputBoundary {

    private final ShowCollectionOutputBoundary showCollectionPresenter;
    private final FileCollectionDataAccessObject collectionDataAccessObject;
    private final ShowCollectionInputData showCollectionInputData;

    public ShowCollectionInteractor(ShowCollectionOutputBoundary showCollectionPresenter, ShowCollectionInputData showCollectionInputData) {
        this.showCollectionPresenter = showCollectionPresenter;
        this.showCollectionInputData = showCollectionInputData;

        try {
            this.collectionDataAccessObject = new FileCollectionDataAccessObject(showCollectionInputData.getFilePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Recipe> execute() {
        List<Recipe> collections = collectionDataAccessObject.load(showCollectionInputData);
        for (Recipe collection: collections) {
            ShowCollectionOutputData showCollectionOutputData = new ShowCollectionOutputData(collection.getName(), collection.getCalories(), collection.getCulture());
        }
        return collections;
    }
}
