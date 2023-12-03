package interface_adapter.add_to_collection;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddCollectionViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Recipes";
    public static final String ADDCOLLECTION_BUTTON_LABEL = "Add To Collection";
    public static final String BACK_BUTTON_LABEL = "Back";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private AddCollectionState state = new AddCollectionState();

    public void setState(AddCollectionState state) {
        this.state = state;
    }

    public AddCollectionViewModel() {
        super("Search Results");
    }

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public AddCollectionState getState() {
        return state;
    }
}
