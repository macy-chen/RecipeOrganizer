import app.Main;
import entity.Ingredient;
import entity.Nutrient;
import entity.Recipe;
import interface_adapter.*;
import interface_adapter.add_to_collection.AddCollectionState;
import interface_adapter.add_to_collection.AddCollectionViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import use_case.search.*;
import view.SearchView;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SearchTest {

    static boolean popUpDiscovered = false;

    public JButton getSearchButton() {
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

        SearchView sv = (SearchView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(2);

        return (JButton) buttons.getComponent(0);
    }

    public JButton getShowCollectionButton() {
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

        SearchView sv = (SearchView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(2);

        return (JButton) buttons.getComponent(1);
    }

    @org.junit.Test
    public void testSearchButtonPresent() throws IOException {
        Main.main(null);
        JButton button = getSearchButton();
        assert (button.getText().equals("Search"));
    }

    @org.junit.Test
    public void testShowCollectionButtonPresent() throws IOException {
        Main.main(null);
        JButton button = getShowCollectionButton();
        assert (button.getText().equals("Show Collection"));
    }

    @org.junit.Test
    public void testSuccessView() {
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData results) {
                assertTrue(results.getSearchResults() != null);
            }

            @Override
            public void prepareFailView(String error) {
                fail();
            }
        };
        SearchInputData searchInputData = new SearchInputData("beef");
        SearchInputBoundary interactor = new SearchInteractor(successPresenter);
        interactor.execute(searchInputData);
    }

    @org.junit.Test
    public void testFailView() {
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData results) {
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.equals("No recipes found."));
            }
        };
        SearchInputData searchInputData = new SearchInputData("hdfskjfhkjsf");
        SearchInputBoundary interactor = new SearchInteractor(successPresenter);
        interactor.execute(searchInputData);
    }

    @org.junit.Test
    public void testRecipeEntity() {
        Ingredient ingr = new Ingredient("name", 18F, "cup");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingr);
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<Nutrient> nutrients = new ArrayList<>();
        Recipe recipe = new Recipe("name", ingredients, nutrients, 18.0F, "culture", 1, "url");
        assertEquals("name", recipe.getName());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals("culture", recipe.getCulture());
        assertEquals(nutrients, recipe.getNutrients());
        assertTrue(recipe.getCalories().equals(18.0F));
        assertTrue(recipe.getPortion().equals(1));
        assertEquals("url", recipe.getUrl());
        recipe.setName("name2");
        assertEquals("name2", recipe.getName());
        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        recipe.setIngredients(ingredients2);
        assertEquals(ingredients2, recipe.getIngredients());
        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<Nutrient> nutrients2 = new ArrayList<Nutrient>();
        recipe.setNutrients(nutrients2);
        recipe.setCulture("culture2");
        assertEquals(nutrients2, recipe.getNutrients());
        assertEquals("culture2", recipe.getCulture());
        recipe.setCalories(19.0F);
        recipe.setPortion(2);
        recipe.setUrl("url2");
        assertEquals("url2", recipe.getUrl());
        assertTrue(recipe.getCalories().equals(19.0F));
        assertTrue(recipe.getPortion().equals(2));
    }

    @org.junit.Test
    public void testSearchPresenterFailViewMethod() {
        SearchViewModel searchViewModel = new SearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        AddCollectionViewModel addCollectionViewModel = new AddCollectionViewModel();
        SearchState searchState = new SearchState();
        searchViewModel.setState(searchState);
        SearchOutputBoundary errorPresenter = new SearchPresenter(searchViewModel, viewManagerModel, addCollectionViewModel);
        SearchInputData searchInputData = new SearchInputData("hdfskjfhkjsf");
        SearchInputBoundary interactor = new SearchInteractor(errorPresenter);
        interactor.execute(searchInputData);
        assertTrue(searchState.getSearchError().equals("No recipes found."));
    }

    @org.junit.Test
    public void testSearchPresenterSuccessViewMethod() {
        SearchViewModel searchViewModel = new SearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        AddCollectionViewModel addCollectionViewModel = new AddCollectionViewModel();
        AddCollectionState addCollectionState = new AddCollectionState();
        addCollectionViewModel.setState(addCollectionState);
        SearchState searchState = new SearchState();
        searchViewModel.setState(searchState);
        SearchOutputBoundary errorPresenter = new SearchPresenter(searchViewModel, viewManagerModel, addCollectionViewModel);
        SearchInputData searchInputData = new SearchInputData("chicken");
        SearchInputBoundary interactor = new SearchInteractor(errorPresenter);

        interactor.execute(searchInputData);
        assertNull(searchState.getSearchError());
        assertTrue(!addCollectionState.getRecipeResults().isEmpty());
    }

    @org.junit.Test
    public void testSearchControllerExecute() {
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData results) {
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.equals("No recipes found."));
            }
        };
        SearchInputBoundary interactor = new SearchInteractor(successPresenter);
        SearchController searchController = new SearchController(interactor);
        searchController.execute("norecipefoundpls");
    }

    @org.junit.Test
    public void testSearchState() {

    }

    @org.junit.Test
    public void testSearchFailPopupDiscovered() throws IOException {
        popUpDiscovered = false;

        Main.main(null);

        JButton button = getSearchButton();


        // since clicking the button should end up displaying a JDialog to the user to report the
        // result, we set a timer, which will execute code necessary to complete the testing.

        //click the button

        createCloseTimer().start();

        button.doClick();


        // will continue execution here after the JDialog is closed

        // confirm a popUp was discovered
        assert (popUpDiscovered);

    }

    private Timer createCloseTimer() {
        ActionListener close = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Window[] windows = Window.getWindows();
                for (Window window : windows) {

                    if (window instanceof JDialog) {

                        JDialog dialog = (JDialog) window;

                        // this ignores old dialogs
                        if (dialog.isVisible()) {
                            String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
                                    .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();
                            System.out.println("message = " + s);

                            // store the information we got from the JDialog
                            SearchTest.popUpDiscovered = true;

                            System.out.println("disposing of..." + window.getClass());
                            window.dispose();
                        }
                    }
                }
            }

        };
        Timer t = new Timer(1000, close);
        t.setRepeats(true);
        return t;
    }
}
