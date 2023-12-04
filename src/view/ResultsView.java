package view;

import app.Main;
import entity.Recipe;
import interface_adapter.search.SearchViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_collection.AddCollectionController;
import interface_adapter.add_to_collection.AddCollectionState;
import interface_adapter.add_to_collection.AddCollectionViewModel;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class ResultsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Search Results";
    private final AddCollectionViewModel addCollectionViewModel;
    private final AddCollectionController addCollectionController;
    final JButton addCollection;
    final JButton back;
    final List<JRadioButton> selectRecipeBoxes;
    private int selectedBox;


    public ResultsView(AddCollectionViewModel addCollectionViewModel, AddCollectionController addCollectionController) {
        this.addCollectionController = addCollectionController;
        this.addCollectionViewModel = addCollectionViewModel;
        addCollectionViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(AddCollectionViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        addCollection = new JButton(AddCollectionViewModel.ADDCOLLECTION_BUTTON_LABEL);
        back = new JButton(AddCollectionViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);
        buttons.add(addCollection);

        int numResults = 5;
        JPanel checkBoxes = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.Y_AXIS));
        checkBoxes.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectRecipeBoxes = new ArrayList<>();
        for (int i = 0; i < numResults; i++) {
            JRadioButton box = new JRadioButton();
            buttonGroup.add(box);
            checkBoxes.add(box);
            selectRecipeBoxes.add(box);
        }

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        setVisible(false);
                        ViewManagerModel viewManagerModel = new ViewManagerModel();
                        SearchViewModel searchViewModel = new SearchViewModel();
                        viewManagerModel.setActiveView(searchViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );

        addCollection.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addCollection)) {
                            for (JRadioButton box : selectRecipeBoxes) {
                                if (box.isSelected()) {
                                    selectedBox = selectRecipeBoxes.indexOf(box);
                                    System.out.println(selectedBox);
                                }
                            }
                            AddCollectionState currentState = addCollectionViewModel.getState();
                            addCollectionController.execute(
                                    selectedBox, currentState.getRecipeResults()
                            );
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(checkBoxes);
        this.add(buttons);
    }
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Error.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AddCollectionState state = (AddCollectionState) evt.getNewValue();
        if (state.getAddCollectionError() != null) {
            JOptionPane.showMessageDialog(this, state.getAddCollectionError());
        } else {
            setFields(state);
        }
    }
    public void setFields(AddCollectionState state) {
        List<Recipe> recipeResults = state.getRecipeResults();
        for (int i = 0; i < recipeResults.size(); i++) {
            String text = "<html>" + recipeResults.get(i).getName() + "<br>" +
                    "Calories: " + recipeResults.get(i).getCalories() +"<html>";
            selectRecipeBoxes.get(i).setText(text);
        }
    }
}
