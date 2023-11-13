package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Search Recipes";
    public static final String KEYWORD_LABEL = "Input Keyword";

    public static final String SEARCH_BUTTON_LABEL = "Search";

    public static final String SHOW_COLLECTION_BUTTON_LABEL = "Show Collection";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private SearchState state = new SearchState();

    public void setState(SearchState state) {
        this.state = state;
    }

    public SearchViewModel() {
        super("search");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchState getState() {
        return state;
    }
}
