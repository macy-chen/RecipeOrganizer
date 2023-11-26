package app;

import interface_adapter.*;
import interface_adapter.add_to_collection.AddCollectionController;
import interface_adapter.add_to_collection.AddCollectionPresenter;
import interface_adapter.add_to_collection.AddCollectionViewModel;
import use_case.add_to_collection.AddCollectionInputBoundary;
import use_case.add_to_collection.AddCollectionInteractor;
import use_case.add_to_collection.AddCollectionOutputBoundary;
import use_case.add_to_collection.AddCollectionCollectionDataAccessInterface;
import view.ResultsView;

import javax.swing.*;
import java.io.IOException;

public class AddCollectionUseCaseFactory {
    private AddCollectionUseCaseFactory() {
    }

    public static ResultsView create(
            ViewManagerModel viewManagerModel,
            AddCollectionViewModel addCollectionViewModel,
            AddCollectionCollectionDataAccessInterface collectionDataAccessObject) {

        try {
            AddCollectionController addCollectionController = createAddCollectionUseCase(viewManagerModel, addCollectionViewModel, collectionDataAccessObject);
            return new ResultsView(addCollectionViewModel, addCollectionController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }
    private static AddCollectionController createAddCollectionUseCase(ViewManagerModel viewManagerModel, AddCollectionViewModel addCollectionViewModel, AddCollectionCollectionDataAccessInterface collectionDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        AddCollectionOutputBoundary addCollectionOutputBoundary = new AddCollectionPresenter(addCollectionViewModel, viewManagerModel);

        AddCollectionInputBoundary addCollectionInteractor = new AddCollectionInteractor(collectionDataAccessObject, addCollectionOutputBoundary);

        return new AddCollectionController(addCollectionInteractor);
    }
}
