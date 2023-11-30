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
    public void save(ShoppingList shoppingList){
        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter(txtPath));

//            String header = String.format("%-20s %-15s %s", "Ingredient", "Amount", "Unit");
//            writer.write(header);
//            writer.newLine();
//            writer.write("---------------------------------------------");
//            writer.newLine();

            writer.write(shoppingList.ingredientstoString());

            writer.close();

        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
