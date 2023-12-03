package interface_adapter.add_to_collection;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_collection.AddCollectionViewModel;
import use_case.add_to_collection.AddCollectionOutputBoundary;
import use_case.add_to_collection.AddCollectionOutputData;
import view.ResultsView;

import java.util.*;

public class AddCollectionPresenter implements AddCollectionOutputBoundary {
    private final AddCollectionViewModel addCollectionViewModel;
    private ViewManagerModel viewManagerModel;

    public AddCollectionPresenter(AddCollectionViewModel addCollectionViewModel, ViewManagerModel viewManagerModel) {
        this.addCollectionViewModel = addCollectionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddCollectionOutputData response) {
        System.out.println(response.getCollectionResults());

        //new
//        AddCollectionState addCollectionState = addCollectionViewModel.getState();
//        addCollectionState.setRecipeResults(response.getCollectionResults());
//        addCollectionViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        AddCollectionState addCollectionState = addCollectionViewModel.getState();
        addCollectionState.setCollectionError(error);
        addCollectionViewModel.firePropertyChanged();
    }
}
