package interface_adapter;

import use_case.ShowCollectionOutputBoundary;
import use_case.ShowCollectionOutputData;

public class ShowCollectionPresenter implements ShowCollectionOutputBoundary {
    private final ShowCollectionViewModel showCollectionViewModel;
    private ViewManagerModel viewManagerModel;

    public ShowCollectionPresenter(ShowCollectionViewModel showCollectionViewModel, ViewManagerModel viewManagerModel) {
        this.showCollectionViewModel = showCollectionViewModel;
        this.viewManagerModel = viewManagerModel;
        //this.showCollectionViewModel = showCollectionViewModel;
    }

    @Override
    public void prepareSuccessView(ShowCollectionOutputData showCollectionOutputData) {
        System.out.println("Show Collection Presenter prepareSuccessView called");
        System.out.println(showCollectionOutputData);
    }

    @Override
    public void prepareFailView(String error) {
        ShowCollectionState showCollectionState = showCollectionViewModel.getState();
        showCollectionState.setError(error);
        showCollectionViewModel.firePropertyChanged();
    }
}
