package interface_adapter;

public class ShowCollectionPresenter {
    private final ShowCollectionController showCollectionController;

    public ShowCollectionPresenter(ShowCollectionController showCollectionController) {
        this.showCollectionController = showCollectionController;
    }

    public void showCollectionRequested() {
        showCollectionController.execute();
    }
}
