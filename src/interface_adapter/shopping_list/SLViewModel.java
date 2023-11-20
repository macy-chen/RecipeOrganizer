package interface_adapter.shopping_list;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SLViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Generate Shopping List";
    public static final String GENERATE_SL_BUTTON_LABEL = "Generate Shopping List";

    private SLState state = new SLState();

    public SLViewModel(){super("shopping list");}

    public void setState(SLState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SLState getState(){
        return state;
    }

}
