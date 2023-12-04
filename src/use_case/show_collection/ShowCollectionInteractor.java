package use_case.show_collection;

import data_access.FileCollectionDataAccessObject;
import entity.Recipe;

import java.io.IOException;
import java.util.List;

public class ShowCollectionInteractor implements ShowCollectionInputBoundary {

    private final ShowCollectionOutputBoundary showCollectionPresenter;
    private final FileCollectionDataAccessObject collectionDataAccessObject;
    private final ShowCollectionInputData showCollectionInputData;

    public ShowCollectionInteractor(ShowCollectionOutputBoundary showCollectionPresenter) throws IOException {
        this.showCollectionPresenter = showCollectionPresenter;
        this.showCollectionInputData = new ShowCollectionInputData();

        this.collectionDataAccessObject = new FileCollectionDataAccessObject(showCollectionInputData.getFilePath());
    }

    public List<Recipe> execute() {
        List<Recipe> collections = collectionDataAccessObject.load(showCollectionInputData);
        ShowCollectionOutputData showCollectionOutputData = null;
        for (Recipe collection : collections) {
            showCollectionOutputData = new ShowCollectionOutputData(collection.getName(), collection.getCalories(), collection.getCulture());
        }
        showCollectionPresenter.prepareSuccessView(showCollectionOutputData);
        return collections;
    }
}
