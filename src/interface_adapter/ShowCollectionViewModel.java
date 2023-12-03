package interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShowCollectionViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Recipe collections";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ShowCollectionState state = new ShowCollectionState();

    public void setState(ShowCollectionState state) {
        this.state = state;
    }

    public ShowCollectionViewModel() {
        super("Recipe collections");
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ShowCollectionState getState() {
        return state;
    }
}
