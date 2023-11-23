package interface_adapter.add_to_collection;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_collection.AddCollectionViewModel;
import use_case.add_to_collection.AddCollectionOutputBoundary;
import use_case.add_to_collection.AddCollectionOutputData;
import view.ResultsView;

import java.util.*;

public class AddCollectionPresenter implements AddCollectionOutputBoundary {
    private final AddCollectionViewModel addCollectionViewModel;
    //private final ShowCollectionViewModel showCollectionViewModel;
    private ViewManagerModel viewManagerModel;

    public AddCollectionPresenter(AddCollectionViewModel addCollectionViewModel, ViewManagerModel viewManagerModel) {
        this.addCollectionViewModel = addCollectionViewModel;
        this.viewManagerModel = viewManagerModel;
        //this.showCollectionViewModel = showCollectionViewModel;
    }

    @Override
    public void prepareSuccessView(AddCollectionOutputData response) {
//        ShowCollectionState showCollectionState = ShowCollectionViewModel.getState();
//        showCollectionState.setCollection(response.getCollectionResults());
//        this.showCollectionViewModel.setState(showCollectionState);
//        showCollectionViewModel.filePropertyChanged();
//
//        viewManagerModel.setActiveView(showCollectionViewModel.getViewName());
//        viewManagerModel.filePropertyChanged();
        System.out.println(response.getCollectionResults());
    }

    @Override
    public void prepareFailView(String error) {
        AddCollectionState addCollectionState = addCollectionViewModel.getState();
        addCollectionState.setCollectionError(error);
        addCollectionViewModel.firePropertyChanged();
    }
}
