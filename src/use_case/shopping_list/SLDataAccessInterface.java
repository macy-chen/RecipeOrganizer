package use_case.shopping_list;

import entity.ShoppingList;

import java.io.IOException;

public interface SLDataAccessInterface {
    public void save(ShoppingList shoppingList) throws IOException;
}
