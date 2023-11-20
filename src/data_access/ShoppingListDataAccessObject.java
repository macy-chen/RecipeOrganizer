package data_access;

import entity.Ingredient;
import entity.ShoppingList;
import use_case.shopping_list.SLDataAccessInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ShoppingListDataAccessObject implements SLDataAccessInterface {

    private final String txtPath;

    public ShoppingListDataAccessObject(String txtPath){
        this.txtPath = txtPath;
    }

    @Override
    public void save(ShoppingList shoppingList) throws IOException {
        ArrayList<Ingredient> ingredients = shoppingList.getIngredients();

        FileWriter file = new FileWriter(txtPath);
        BufferedWriter bw = new BufferedWriter(file);
        bw.write("Ingredient \t Amount \t Unit \n"); //header
        bw.write("-----------------------");

        bw.write(shoppingList.toString());
    }
}
