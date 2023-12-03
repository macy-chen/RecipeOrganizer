package view;

import entity.Recipe;
import entity.RecipeCollection;
import interface_adapter.add_to_collection.AddCollectionState;
import interface_adapter.add_to_collection.AddCollectionViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.shopping_list.SLController;
import interface_adapter.shopping_list.SLState;
import interface_adapter.shopping_list.SLViewModel;
import interface_adapter.show_collection.ShowCollectionController;
import interface_adapter.show_collection.ShowCollectionViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
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

    private final JButton generateShoppingList;

    private final SLViewModel slViewModel;
    private final SLController slController;


    public SearchView(SearchController controller, SearchViewModel searchViewModel,
                      ShowCollectionController showCollectionController, ShowCollectionViewModel showCollectionViewModel,
                      SLController slController, SLViewModel slViewModel, AddCollectionViewModel addCollectionViewModel) {
        this.searchController = controller;
        this.searchViewModel = searchViewModel;
        this.showCollectionViewModel = showCollectionViewModel;
        this.showCollectionController = showCollectionController;
        searchViewModel.addPropertyChangeListener(this);

        this.slController = slController;
        this.slViewModel = slViewModel;

        JLabel title = new JLabel(SearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel keywordInfo = new LabelTextPanel(new JLabel(SearchViewModel.KEYWORD_LABEL), keywordInputField);

        JPanel buttons = new JPanel();
        search = new JButton(SearchViewModel.SEARCH_BUTTON_LABEL);
        showCollection = new JButton(SearchViewModel.SHOW_COLLECTION_BUTTON_LABEL);
        generateShoppingList = new JButton(SLViewModel.GENERATE_SL_BUTTON_LABEL);
        buttons.add(search);
        buttons.add(showCollection);
        buttons.add(generateShoppingList);

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

        generateShoppingList.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(generateShoppingList)) { //when clicked


                            //SLState currentState = slViewModel.getState();
                            AddCollectionState currentState = addCollectionViewModel.getState(); //only works from recently added collection?
                            List<Recipe> recipes = currentState.getRecipeResults();


                            //

//                            RecipeCollection c;
//                            if (recipes.isEmpty()){
//                                c = new RecipeCollection();
//                            }
//                            else {
//                                ArrayList<Recipe> recipeArrayList = new ArrayList<>(recipes);
//                                c = new RecipeCollection(recipeArrayList);
//                            }

                            try {
                                slController.execute("./recipe");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        SLState slState = slViewModel.getState();
                        if (slState.getShoppingListError() == null){ //no error
                            JOptionPane.showMessageDialog(null, slState.getShoppingList().ingredientstoString());
                        }
                        else{ //have error
                            JOptionPane.showMessageDialog(null, slState.getShoppingListError());
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
