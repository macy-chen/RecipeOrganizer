package use_case.shopping_list;

import entity.ShoppingList;

public class  SLOutputData {
    private final ShoppingList shoppingList;

    private boolean useCaseFailed;

    public SLOutputData(ShoppingList shoppingList, boolean useCaseFailed) {
        this.shoppingList = shoppingList;
        this.useCaseFailed = useCaseFailed;
    }


    public ShoppingList getShoppingList() {
        return shoppingList;
    }
}
