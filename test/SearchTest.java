
import app.Main;
import use_case.search.*;
import view.SearchView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.*;

public class SearchTest {

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
        assert(button.getText().equals("Search"));
    }

    @org.junit.Test
    public void testShowCollectionButtonPresent() throws IOException {
        Main.main(null);
        JButton button = getShowCollectionButton();
        assert(button.getText().equals("Show Collection"));
    }

    @org.junit.Test
    public void testSuccessTest() {
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
}
