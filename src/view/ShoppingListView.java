package view;

import entity.RecipeCollection;
import interface_adapter.shopping_list.SLController;
import interface_adapter.shopping_list.SLState;
import interface_adapter.shopping_list.SLViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class ShoppingListView extends JPanel implements ActionListener, PropertyChangeListener {
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */

    public final String viewName = "shopping list";

    //need to connect to ViewCollectionViewModel later!
    private final SLViewModel slViewModel;

    private final SLController slController;

    private final JButton generateSL;

    public ShoppingListView(SLViewModel slViewModel, SLController slController) { //TODO: ViewController & ViewViewModel
        this.slViewModel = slViewModel;
        this.slController = slController;

        JPanel buttons = new JPanel();
        generateSL = new JButton(SLViewModel.GENERATE_SL_BUTTON_LABEL);
        buttons.add(generateSL);

        //set the view controller & viewviewmodel

        generateSL.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(generateSL)) {
                            SLState currentState = slViewModel.getState();
                            RecipeCollection c = new RecipeCollection();
                            slController.execute(c); //need recipe list -- from view collection state
                        }
                        SLState slState = slViewModel.getState();
                        JOptionPane.showMessageDialog(null, slState.getShoppingList());
                    }
                }
        );
    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Generate Shopping List not implemented yet.");
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
            if (Objects.equals(evt.getPropertyName(), "state")){
                SLState state = (SLState) evt.getNewValue();
                if (state.getShoppingListError() != null) {
                    JOptionPane.showMessageDialog(this, state.getShoppingListError());
                }
            }
    }
}
