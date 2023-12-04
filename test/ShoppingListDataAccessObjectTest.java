import data_access.ShoppingListDataAccessObject;
import entity.Ingredient;
import entity.ShoppingList;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

public class ShoppingListDataAccessObjectTest {


    ShoppingListDataAccessObject shoppingListDataAccessObject = new ShoppingListDataAccessObject("./ShoppingListTest.txt");

    private ShoppingList generateSL1(){ //creates ingredient list --> shopping List
        Ingredient ingredient1 = new Ingredient("Bean", 100.0F, "g");
        Ingredient ingredient2 = new Ingredient("Corn", 1.5F, "tsp");
        ArrayList<Ingredient> ingredientArrayList1 = new ArrayList<>();
        ingredientArrayList1.add(ingredient1);
        ingredientArrayList1.add(ingredient2);
        return new ShoppingList(ingredientArrayList1);
    }

    public static ShoppingList generateSL2(){ //credients ingredient list --> shopping List
        Ingredient ingredient1 = new Ingredient("Pork", 30F, "g");
        Ingredient ingredient2 = new Ingredient("Peas", 2F, "cup");
        ArrayList<Ingredient> ingredientArrayList1 = new ArrayList<>();
        ingredientArrayList1.add(ingredient1);
        ingredientArrayList1.add(ingredient2);
        return new ShoppingList(ingredientArrayList1);
    }


    private ShoppingList generateSL3(){ //one ingredient
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients = generateSL1().getIngredients();
        ingredients.remove(1);
        return new ShoppingList(ingredients);
    }

    @Test
    public void testFileExists(){
        shoppingListDataAccessObject.save(generateSL1());
        assert (new File("./ShoppingListTest.txt").exists());
    }

    @Test
    public void testMultipleIngredients(){
        shoppingListDataAccessObject.save(generateSL2());
        File f = new File("./ShoppingListTest.txt");
        String s;
        String last;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("./ShoppingListTest.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            s = reader.readLine();
            s = reader.readLine();
            s = reader.readLine();
            last = reader.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assert(s.contains("Pork") && last.contains("cup"));

    }

    @Test
    public void testOneIngredient(){
        shoppingListDataAccessObject.save(generateSL3());
        File f = new File("./ShoppingList.txt");
        String s;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("./ShoppingListTest.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            s = reader.readLine();
            s = reader.readLine();
            s = reader.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assert(s.contains("Bean") );
    }








}