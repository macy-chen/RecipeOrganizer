package view;

import entity.Recipe;
import interface_adapter.add_to_collection.AddCollectionController;
import interface_adapter.add_to_collection.AddCollectionState;
import interface_adapter.add_to_collection.AddCollectionViewModel;

import java.beans.PropertyChangeEvent;
import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;

public class ResultsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Search Results";
    private final AddCollectionViewModel addCollectionViewModel;
    private final AddCollectionController addCollectionController;
    final JButton addCollection;
    final List<JCheckBox> selectRecipeBoxes;
    final List<Integer> selectedBoxes;

    public ResultsView(AddCollectionViewModel addCollectionViewModel, AddCollectionController addCollectionController) {
        this.addCollectionController = addCollectionController;
        this.addCollectionViewModel = addCollectionViewModel;
        addCollectionViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(AddCollectionViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        addCollection = new JButton(AddCollectionViewModel.ADDCOLLECTION_BUTTON_LABEL);
        buttons.add(addCollection);

        int numResults = 5;
        JPanel checkBoxes = new JPanel();
        checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.Y_AXIS));
        checkBoxes.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedBoxes = new ArrayList<>();
        selectRecipeBoxes = new ArrayList<>();
        for (int i = 0; i < numResults; i++) {
            JCheckBox box = new JCheckBox();
            selectRecipeBoxes.add(box);
            checkBoxes.add(box);
        }

        addCollection.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addCollection)) {
                            AddCollectionState currentState = addCollectionViewModel.getState();
                            addCollectionController.execute(
                                    selectedBoxes, currentState.getRecipeResults()
                            );
                        }
                    }
                }
        );

        for (JCheckBox box:selectRecipeBoxes) {
            box.addItemListener(
                    new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                selectedBoxes.add(selectRecipeBoxes.indexOf(box));
                            }
                            else if (e.getStateChange() == ItemEvent.DESELECTED) {
                                selectedBoxes.remove(Integer.valueOf(selectRecipeBoxes.indexOf(box)));
                            }
                            System.out.println(selectedBoxes);
                        }
                    });
        }
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
            selectRecipeBoxes.get(i).setText(recipeResults.get(i).getName());
        }
    }
}
