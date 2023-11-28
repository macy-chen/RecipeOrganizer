package interface_adapter;

import entity.RecipeCollection;
import use_case.ShowCollectionInputData;
import use_case.ShowCollectionInteractor;
import use_case.ShowCollectionOutputBoundary;
import use_case.ShowCollectionOutputData;

import java.util.ArrayList;

public class ShowCollectionPresenter implements ShowCollectionOutputBoundary {

    private final ShowCollectionViewModel showCollectionViewModel;
    private final ViewManagerModel viewManagerModel;

    public ShowCollectionPresenter(ShowCollectionViewModel showCollectionViewModel, ViewManagerModel viewManagerModel) {
        this.showCollectionViewModel = showCollectionViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    public void prepareSuccessView(ShowCollectionOutputData showCollectionOutputData) {
        System.out.println(showCollectionOutputData.toString());

    }

    @Override
    public void prepareSuccessView() {

    }
}
