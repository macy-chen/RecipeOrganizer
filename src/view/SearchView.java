package view;

import entity.Recipe;
import interface_adapter.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "search";

    private final SearchViewModel searchViewModel;
    private final JTextField keywordInputField = new JTextField(15);
    private final SearchController searchController;

    private final JButton search;
    private final JButton showCollection;
    private final ShowCollectionViewModel showCollectionViewModel;
    private final ShowCollectionController showCollectionController;


    public SearchView(SearchController controller, SearchViewModel searchViewModel,
                      ShowCollectionController showCollectionController, ShowCollectionViewModel showCollectionViewModel) {
        this.searchController = controller;
        this.searchViewModel = searchViewModel;
        this.showCollectionViewModel = showCollectionViewModel;
        this.showCollectionController = showCollectionController;
        searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel keywordInfo = new LabelTextPanel(new JLabel(SearchViewModel.KEYWORD_LABEL), keywordInputField);

        JPanel buttons = new JPanel();
        search = new JButton(SearchViewModel.SEARCH_BUTTON_LABEL);
        showCollection = new JButton(SearchViewModel.SHOW_COLLECTION_BUTTON_LABEL);
        buttons.add(search);
        buttons.add(showCollection);

        search.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            SearchState currentState = searchViewModel.getState();

                            searchController.execute(currentState.getKeyword());
                        }
                    }
                }
        );
        showCollection.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(showCollection)) {
                            ShowCollectionViewModel showCollectionViewModel = new ShowCollectionViewModel();

                            ShowCollectionView showCollectionView = new ShowCollectionView(showCollectionViewModel, showCollectionController);
                            // Call the controller to execute the use case

                        }
                    }
                }
        );

        keywordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = searchViewModel.getState();
                        String text = keywordInputField.getText() + e.getKeyChar();
                        currentState.setKeyword(text);
                        searchViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(keywordInfo);
        this.add(buttons);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState state = (SearchState) evt.getNewValue();
        if (state.getSearchError() != null) {
            JOptionPane.showMessageDialog(this, state.getSearchError());
            }
    }
}
