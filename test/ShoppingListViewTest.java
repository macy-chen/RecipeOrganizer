import app.Main;
import entity.Ingredient;
import entity.Recipe;
import entity.RecipeCollection;
import view.ShoppingListView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListViewTest {
    static String message = "";
    static boolean popUpDiscovered = false;
    RecipeCollection recipeCollection = new RecipeCollection();

    public JButton getButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        ShoppingListView shoppingListView = (ShoppingListView) jp2.getComponent(0);

        JPanel buttons = (JPanel) shoppingListView.getComponent(1);

        return (JButton) buttons.getComponent(0); // this should be the clear button
    }

    public void addRecipe2Ingredients(){
        Ingredient ingredient1 = new Ingredient("Pepper", 100.0F, "g");
        Ingredient ingredient2 = new Ingredient("Olive Oil", 1.5F, "tsp");
        ArrayList<Ingredient> ingredientArrayList1 = new ArrayList<>();
        ingredientArrayList1.add(ingredient1);
        ingredientArrayList1.add(ingredient2);

        Recipe r1 = new Recipe();
        r1.setIngredients(ingredientArrayList1);

        this.recipeCollection.addRecipe(r1);
    }

    public void addRecipe8Ingredients(){ //has a 2 repeat ingredients w/ addRecipe3Ingredients
        Ingredient ingredient1 = new Ingredient("Peas", 0.5F, "cup");
        Ingredient ingredient2 = new Ingredient("Ketchup", 3F, "tbsp");
        Ingredient ingredient3 = new Ingredient("Carrot", 2F, "cup");
        Ingredient ingredient4 = new Ingredient("Soy Sauce", 3F, "tbsp");
        Ingredient ingredient5 = new Ingredient("Egg", 2F, "eggs");
        Ingredient ingredient6 = new Ingredient("Garlic", 5F, "tbsp");
        Ingredient ingredient7 = new Ingredient("Sesame oil", 0.5F, "tsp");
        Ingredient ingredient8 = new Ingredient("Rice", 2F, "cup");
        ArrayList<Ingredient> ingredientArrayList2 = new ArrayList<>();
        ingredientArrayList2.add(ingredient1);
        ingredientArrayList2.add(ingredient2);
        ingredientArrayList2.add(ingredient3);
        ingredientArrayList2.add(ingredient4);
        ingredientArrayList2.add(ingredient5);
        ingredientArrayList2.add(ingredient6);
        ingredientArrayList2.add(ingredient7);
        ingredientArrayList2.add(ingredient8);

        Recipe r3 = new Recipe();
        r3.setIngredients(ingredientArrayList2);

        recipeCollection.addRecipe(r3);
    }

    /**
     *
     * Test that the Generate Shopping List button is present and where it is expected to be
     */
    @org.junit.Test
    public void testGenerateShoppingListButtonPresent() {
        Main.main(null);
        JButton button = getButton();
        assert(button.getText().equals("Generate Shopping List"));
    }

    /**
     *
     * Test that a popup appears with the correct shopping list display
     */
    @org.junit.Test
    public void testPopupPresent() {
        Main.main(null);
        JButton button = getButton();
        assert(button.getText().equals("Generate Shopping List"));
    }

    /**
     *
     * Test that a popup appears with the correct shopping list display
     */
    @org.junit.Test
    public void testPopupContent2Ingredients() {
        popUpDiscovered = false;

        Main.main(null);
        JFrame app = null;

        JButton button = getButton();


        // since clicking the button should end up displaying a JDialog to the user to report the
        // result, we set a timer, which will execute code necessary to complete the testing.
        createCloseTimer().start();

        //click the button
        button.doClick();

        // will continue execution here after the JDialog is closed

        assert(popUpDiscovered); // confirm a popUp was discovered
        assertTrue(message.contains("Pepper") && message.contains("Rice"));
    }

    /**
     *
     * Test that a popup appears with the correct error display
     */
    @org.junit.Test
    public void testPopupFailView() {
        Main.mainEmpty(null);

        JFrame app = null;

        JButton button = getButton();


        // since clicking the button should end up displaying a JDialog to the user to report the
        // result, we set a timer, which will execute code necessary to complete the testing.
        createCloseTimer().start();

        //click the button
        button.doClick();

        assert(popUpDiscovered);
        assertTrue(message.contains("empty"));
    }

    private Timer createCloseTimer() {
        ActionListener close = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Window[] windows = Window.getWindows();
                for (Window window : windows) {

                    if (window instanceof JDialog) {

                        JDialog dialog = (JDialog)window;

                        // this ignores old dialogs
                        if (dialog.isVisible()) {
                            String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
                                    .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();
                            System.out.println("message = " + s);

                            // store the information we got from the JDialog
                            ShoppingListViewTest.message = s;
                            ShoppingListViewTest.popUpDiscovered = true;

                            System.out.println("disposing of..." + window.getClass());
                            window.dispose();
                        }
                    }
                }
            }

        };

        Timer t = new Timer(1000, close);
        t.setRepeats(false);
        return t;
    }

}