package app;


import entity.ShoppingList;
import entity.ShoppingListFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_collection.AddCollectionViewModel;
import interface_adapter.shopping_list.SLController;
import interface_adapter.shopping_list.SLPresenter;
import interface_adapter.shopping_list.SLViewModel;
import use_case.shopping_list.SLDataAccessInterface;
import use_case.shopping_list.SLInputBoundary;
import use_case.shopping_list.SLInteractor;
import use_case.shopping_list.SLOutputBoundary;

import java.io.IOException;

public class ShoppingListUseCaseFactory {

    private ShoppingListUseCaseFactory(){}

    public static SLController createShoppingListUseCase(ViewManagerModel viewManagerModel,
                                                         SLViewModel slViewmodel,
                                                         AddCollectionViewModel addCollectionViewModel,
                                                         SLDataAccessInterface slDataObject) throws IOException{
        SLOutputBoundary slOutputBoundary = new SLPresenter(viewManagerModel, slViewmodel, addCollectionViewModel);
        ShoppingListFactory shoppingListFactory = new ShoppingListFactory();

        SLInputBoundary slInteractor= new SLInteractor(slDataObject, slOutputBoundary, shoppingListFactory);

        return new SLController(slInteractor);

    }

}
