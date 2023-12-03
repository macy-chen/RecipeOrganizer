package interface_adapter;

import entity.Recipe;
import use_case.ShowCollectionInputBoundary;
import use_case.ShowCollectionInteractor;

import java.util.List;

public class ShowCollectionController {

    private final ShowCollectionInteractor showCollectionInteractor;

    public ShowCollectionController(ShowCollectionInteractor showCollectionInteractor) {
        this.showCollectionInteractor = showCollectionInteractor;
    }

    public List<Recipe> execute() {

        return showCollectionInteractor.execute();
    }

}
