import app.Main;

import use_case.show_collection.*;
import view.SearchView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.*;

public class ShowCollectionTest {
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
    public void testShowCollectionButtonPresent() throws IOException {
        Main.main(null);
        JButton button = getShowCollectionButton();
        assert(button.getText().equals("Show Collection"));
    }

    @org.junit.Test
    public void testSuccessTest() throws IOException {
        ShowCollectionOutputBoundary successPresenter = new ShowCollectionOutputBoundary() {
            @Override
            public void prepareSuccessView(ShowCollectionOutputData results) {
                assertTrue(results.getName() != null);
            }

            @Override
            public void prepareFailView(String error) {
                fail();
            }

        };
        ShowCollectionInputBoundary interactor = new ShowCollectionInteractor(successPresenter);
        interactor.execute();
    }

    @org.junit.Test
    public void showCollectionOutputDataGetCaloriesTest() {
        ShowCollectionOutputData showCollectionOutputData = new ShowCollectionOutputData("n1", 10.0f, "c1");
        assert (showCollectionOutputData.getCalories().equals(10.0f));
    }

    @org.junit.Test
    public void showCollectionOutputDataGetCultureTest() {
        ShowCollectionOutputData showCollectionOutputData = new ShowCollectionOutputData("n1", 10.0f, "c1");
        assert (showCollectionOutputData.getCulture().equals("c1"));
    }
}
