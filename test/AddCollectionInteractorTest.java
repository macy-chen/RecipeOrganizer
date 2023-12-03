import data_access.FileCollectionDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_collection.*;
import interface_adapter.shopping_list.SLPresenter;
import org.junit.Test;
import use_case.add_to_collection.*;
import use_case.search.*;
import use_case.shopping_list.SLInteractor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AddCollectionInteractorTest {

    private List<Recipe> generateRecipeResults() {
        ArrayList<Nutrient> nutrients = new ArrayList<>();
        Nutrient n1 = new Nutrient("n1", 10.1f, "Macro");
        nutrients.add(n1);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        Ingredient i1 = new Ingredient("i1", 20.0f, "g");
        ingredients.add(i1);

        Recipe recipe1 = new Recipe("r1", ingredients, nutrients, 30.0f, "c1", 1, "url1");
        Recipe recipe2 = new Recipe("r2", ingredients, nutrients, 30.0f, "c2", 2, "url2");
        Recipe recipe3 = new Recipe("r3", ingredients, nutrients, 30.0f, "c3", 3, "url3");
        Recipe recipe4 = new Recipe("r4", ingredients, nutrients, 30.0f, "c4", 4, "url4");
        Recipe recipe5 = new Recipe("r5", ingredients, nutrients, 30.0f, "c5", 5, "url5");

        List<Recipe> recipeResults = new ArrayList<>();
        recipeResults.add(recipe1);
        recipeResults.add(recipe2);
        recipeResults.add(recipe3);
        recipeResults.add(recipe4);
        recipeResults.add(recipe5);

        return recipeResults;
    }
    public void addCollectionInteractorTest() throws IOException {
        int selectedBox = 3;
        AddCollectionInputData addCollectionInputData = new AddCollectionInputData(selectedBox, generateRecipeResults());
        FileCollectionDataAccessObject collectionDataAccessObject = new FileCollectionDataAccessObject("./recipe");
        AddCollectionViewModel addCollectionViewModel = new AddCollectionViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        AddCollectionPresenter addCollectionPresenter = new AddCollectionPresenter(addCollectionViewModel, viewManagerModel);
        AddCollectionInteractor addCollectionInteractor = new AddCollectionInteractor(collectionDataAccessObject,addCollectionPresenter);

        addCollectionInteractor.execute(addCollectionInputData);
    }

    @Test
    public void testFileExists() throws IOException {
        addCollectionInteractorTest();
        assert (new File("./recipe").exists());
    }

    @org.junit.Test
    public void testHeader() throws IOException {
        addCollectionInteractorTest();
        File csvFile = new File("./recipe");
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();
            assert header.equals("name,culture,calories");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testName() throws IOException {
        addCollectionInteractorTest();
        File csvFile = new File("./recipe");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                assert col[0].equals("r4");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCulture() throws IOException {
        addCollectionInteractorTest();
        File csvFile = new File("./recipe");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                assert col[1].equals("c4");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testCalories() throws IOException {
        addCollectionInteractorTest();
        File csvFile = new File("./recipe");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                assert col[2].equals("30.0");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Recipe generateRecipe() {
        ArrayList<Nutrient> nutrients = new ArrayList<>();
        Nutrient n1 = new Nutrient("n1", 10.1f, "Macro");
        nutrients.add(n1);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        Ingredient i1 = new Ingredient("i1", 20.0f, "g");
        Recipe recipe = new Recipe("r1", ingredients, nutrients, 30.0f, "c1", 1, "url1");
        return recipe;
    }

    @org.junit.Test
    public void testAddCollectionFailView() throws IOException {
        AddCollectionOutputBoundary successPresenter = new AddCollectionOutputBoundary() {
            @Override
            public void prepareSuccessView(AddCollectionOutputData results) {
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.equals("r1 already exists in collection."));
            }
        };
        int selectedBox = 0;
        FileCollectionDataAccessObject collectionDataAccessObject = new FileCollectionDataAccessObject("./recipe");
        collectionDataAccessObject.save(generateRecipe());
        AddCollectionInputData addCollectionInputData = new AddCollectionInputData(selectedBox, generateRecipeResults());
        AddCollectionInteractor addCollectionInteractor = new AddCollectionInteractor(collectionDataAccessObject,successPresenter);
        addCollectionInteractor.execute(addCollectionInputData);
    }
}
