package interface_adapter;

import use_case.SearchInputBoundary;
import use_case.ShowCollectionInputBoundary;
import use_case.ShowCollectionInputData;

public class ShowCollectionController {

    final ShowCollectionInputBoundary showCollectionInteractor;

    public ShowCollectionController(ShowCollectionInputBoundary showCollectionInteractor) {
        this.showCollectionInteractor = showCollectionInteractor;
    }

    public void execute(ShowCollectionInputData showCollectionInputData) {
        showCollectionInteractor.execute(showCollectionInputData);
    }
}
