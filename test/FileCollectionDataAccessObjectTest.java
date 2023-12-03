import app.Main;
import data_access.FileCollectionDataAccessObject;
import entity.Ingredient;
import entity.Nutrient;
import entity.Recipe;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileCollectionDataAccessObjectTest {
    FileCollectionDataAccessObject collectionDataAccessObject = new FileCollectionDataAccessObject("./recipe");

    public FileCollectionDataAccessObjectTest() throws IOException {
    }

    private Recipe generateRecipes() {
        ArrayList<Nutrient> nutrients = new ArrayList<>();
        Nutrient n1 = new Nutrient("n1", 10.1f, "Macro");
        nutrients.add(n1);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        Ingredient i1 = new Ingredient("i1", 20.0f, "g");
        Recipe recipe = new Recipe("r1", ingredients, nutrients, 30.0f, "c1", 1, "url1");
        return recipe;
    }

    @Test
    public void testFileExists() {
        collectionDataAccessObject.save(generateRecipes());
        assert (new File("./recipe").exists());
    }

    @Test
    public void testHeader() {
        collectionDataAccessObject.save(generateRecipes());
        File csvFile = new File("./recipe");
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();
            assert header.equals("name,culture,calories");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testName() {
        collectionDataAccessObject.save(generateRecipes());
        File csvFile = new File("./recipe");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                assert col[0].equals("r1");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCulture() {
        collectionDataAccessObject.save(generateRecipes());
        File csvFile = new File("./recipe");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                assert col[1].equals("c1");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testCalories() {
        collectionDataAccessObject.save(generateRecipes());
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
}


