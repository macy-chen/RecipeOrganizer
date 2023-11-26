package interface_adapter.shopping_list;

import interface_adapter.ViewManagerModel;
import use_case.shopping_list.SLOutputBoundary;
import use_case.shopping_list.SLOutputData;

public class SLPresenter implements SLOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private SLViewModel slViewModel;

    public SLPresenter(ViewManagerModel viewManagerModel, SLViewModel slViewModel){ //TODO: update to work with current view model (ViewCollection?)
        this.viewManagerModel = viewManagerModel;
        this.slViewModel = slViewModel;
    }

    public SLPresenter() {

    }

    @Override
    public void prepareFailView(String error) {
        SLState slState = slViewModel.getState();
        slState.setShoppingListError(error);
        slViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(SLOutputData response) {

        SLState slState = slViewModel.getState();
        slState.setShoppingList(response.getShoppingList());
        this.slViewModel.setState(slState);
        slViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(slViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public SLViewModel getSlViewModel() {
        return slViewModel;
    }
}
