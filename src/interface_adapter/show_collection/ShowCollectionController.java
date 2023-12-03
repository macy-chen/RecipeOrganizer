package interface_adapter.show_collection;

import entity.Recipe;
import use_case.show_collection.ShowCollectionInteractor;

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
