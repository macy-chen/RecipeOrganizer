import app.Main;
import data_access.ShoppingListDataAccessObject;
import entity.Ingredient;
import entity.Recipe;
import entity.RecipeCollection;
import entity.ShoppingList;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListDataAccessObjectTest {


    ShoppingListDataAccessObject shoppingListDataAccessObject = new ShoppingListDataAccessObject("./ShoppingList.txt");

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
        assert (new File("./ShoppingList.txt").exists());
    }

    @Test
    public void testMultipleIngredients(){
        shoppingListDataAccessObject.save(generateSL2());
        File f = new File("./ShoppingList.txt");
        //TODO: add assert to check contents
    }

    @Test
    public void testOneIngredient(){
        shoppingListDataAccessObject.save(generateSL3());
        File f = new File("./ShoppingList.txt");
        //TODO: add assert to formally check contents
    }








}