import data_access.ShoppingListDataAccessObject;
import entity.Ingredient;
import entity.Recipe;
import entity.RecipeCollection;
import entity.ShoppingListFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.shopping_list.SLPresenter;
import interface_adapter.shopping_list.SLState;
import interface_adapter.shopping_list.SLViewModel;
import use_case.shopping_list.SLDataAccessInterface;
import use_case.shopping_list.SLInputData;
import use_case.shopping_list.SLInteractor;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
public class SLInteractorTest {

    private RecipeCollection recipeCollection = new RecipeCollection();

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

    public void addRecipe3Ingredients(){
        Ingredient ingredient3 = new Ingredient("Peas", 1F, "cup");
        Ingredient ingredient4 = new Ingredient("Rice", 1F, "cup");
        Ingredient ingredient5 = new Ingredient("Onion", 0.25F, "cup");
        ArrayList<Ingredient> ingredientArrayList2 = new ArrayList<>();
        ingredientArrayList2.add(ingredient3);
        ingredientArrayList2.add(ingredient4);
        ingredientArrayList2.add(ingredient5);

        Recipe r2 = new Recipe();
        r2.setIngredients(ingredientArrayList2);

        recipeCollection.addRecipe(r2);
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

    public void addRecipeUnusualMeasurement(){ //has unusual unit repeat with addRecipe3Ingredients()
        Ingredient ingredient1 = new Ingredient("Onion", 5F, "tbsp");
        Ingredient ingredient2 = new Ingredient("Cream", 15F, "mL");
        Ingredient ingredient3 = new Ingredient("Tomato", 2F, "cup");
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        ingredientArrayList.add(ingredient1);
        ingredientArrayList.add(ingredient2);
        ingredientArrayList.add(ingredient3);

        Recipe r3 = new Recipe();
        r3.setIngredients(ingredientArrayList);

        recipeCollection.addRecipe(r3);
    }



//    @Test
//    public void twoRecipe4SLTest(){ //2 recipes, 5 ingredients (no repeat)
//        addRecipe2Ingredients();
//        addRecipe3Ingredients();
//        SLInputData slInputData = new SLInputData("./recipe");
//        SLDataAccessInterface slDAO = new ShoppingListDataAccessObject("./ShoppingListTest.txt");
//        ShoppingListFactory slFactory = new ShoppingListFactory();
//        SLViewModel slViewModel = new SLViewModel();
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        SLPresenter slPresenter = new SLPresenter(viewManagerModel, slViewModel);
//        SLInteractor interactor = new SLInteractor(slDAO, slPresenter, slFactory);
//
//        try {
//            interactor.execute(slInputData);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        assertEquals("shopping list", viewManagerModel.getActiveView()); //sees if view is changed
//        assertTrue (new File("./ShoppingListTest.txt").exists());
//        //check if file exists...
//    }

//    @Test
//    public void twoRecipe11SLTest(){ //2 recipes, 3 + 8 ingredients, 2 ingredient repeats
//        addRecipe3Ingredients();
//        addRecipe8Ingredients();
//        SLInputData slInputData = new SLInputData(recipeCollection);
//        SLDataAccessInterface slDAO = new ShoppingListDataAccessObject("./ShoppingListTest.txt");
//        ShoppingListFactory slFactory = new ShoppingListFactory();
//        SLViewModel slViewModel = new SLViewModel();
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        SLPresenter slPresenter = new SLPresenter(viewManagerModel, slViewModel);
//        SLInteractor interactor = new SLInteractor(slDAO, slPresenter, slFactory);
//
//        try {
//            interactor.execute(slInputData);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        assertEquals("shopping list", viewManagerModel.getActiveView()); //sees if view is changed
//        assertTrue (new File("./ShoppingListTest.txt").exists());
//
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader("./ShoppingListTest.txt"));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        Stream<String> line = reader.lines();
//
//        assertEquals((2+3+8-2), line.count()); //2 for header + ---, 1 extra because each ingredientToString gives \n
//    }

//    @Test
//    public void sameIngredientDifferentMeasurement(){ //3+3 ingredients (2 with different measurements), 2 recipes
//        addRecipe3Ingredients();
//        addRecipeUnusualMeasurement();
//        SLInputData slInputData = new SLInputData(recipeCollection);
//        SLDataAccessInterface slDAO = new ShoppingListDataAccessObject("./ShoppingListTest.txt");
//        ShoppingListFactory slFactory = new ShoppingListFactory();
//        SLViewModel slViewModel = new SLViewModel();
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        SLPresenter slPresenter = new SLPresenter(viewManagerModel, slViewModel);
//        SLInteractor interactor = new SLInteractor(slDAO, slPresenter, slFactory);
//
//        try {
//            interactor.execute(slInputData);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        assertEquals("shopping list", viewManagerModel.getActiveView()); //sees if view is changed
//        assertTrue (new File("./ShoppingListTest.txt").exists());
//        assertEquals("shopping list", viewManagerModel.getActiveView()); //sees if view is changed
//        assertTrue (new File("./ShoppingListTest.txt").exists());
//
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader("./ShoppingListTest.txt"));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        Stream<String> line = reader.lines();
//
//        assertEquals((2+6), line.count()); //2 for header + ---, 1 extra because each ingredientToString gives \n
//    }

    @org.junit.Test
    public void testEmptyRecipeCollection(){ //emptySL
        SLInputData slInputData = new SLInputData("./recipe_empty_test");
        SLDataAccessInterface slDAO = new ShoppingListDataAccessObject("./ShoppingListTest.txt");
        ShoppingListFactory slFactory = new ShoppingListFactory();
        SLViewModel slViewModel = new SLViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SLPresenter slPresenter = new SLPresenter(viewManagerModel, slViewModel);
        SLInteractor interactor = new SLInteractor(slDAO, slPresenter, slFactory);

        try {
            interactor.execute(slInputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SLState tempState = slPresenter.getSlViewModel().getState();

        assertEquals("Recipe Collection is empty", tempState.getShoppingListError());
    }



}