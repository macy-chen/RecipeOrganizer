package view;

import entity.RecipeCollection;
import interface_adapter.shopping_list.SLController;
import interface_adapter.shopping_list.SLState;
import interface_adapter.shopping_list.SLViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
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

    public ShoppingListView(SLViewModel slViewModel, SLController slController) { //TODO: take from addtoCollectionState
        this.slViewModel = slViewModel;
        this.slController = slController;

        JPanel buttons = new JPanel();
        generateSL = new JButton(SLViewModel.GENERATE_SL_BUTTON_LABEL);
        buttons.add(generateSL);

        JLabel title = new JLabel(SLViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);
        this.add(buttons);


        //set the view controller & viewviewmodel

        generateSL.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(generateSL)) { //when clicked
                            SLState currentState = slViewModel.getState(); //TODO: take from addToCollectionState?
                            RecipeCollection c = currentState.getRecipeCollection();

                            try {
                                slController.execute(c); //need recipe list -- from view collection state
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
    }

    public ShoppingListView(SLViewModel slViewModel){
        this.slViewModel = slViewModel;
        this.slController = null;

        JPanel buttons = new JPanel();
        generateSL = new JButton(SLViewModel.GENERATE_SL_BUTTON_LABEL);
        buttons.add(generateSL);



        JLabel title = new JLabel(SLViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);
        this.add(buttons);

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
