package interface_adapter.shopping_list;

public class SLState {
    private String shoppingList = "";
    private String shoppingListError = null;


    public SLState(SLState copy){
        shoppingList = copy.shoppingList;
        shoppingListError = copy.shoppingListError;
    }

    public SLState(){
    }

    public String getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(String shoppingList) {
        this.shoppingList = shoppingList;
    }

    public String getShoppingListError() {
        return shoppingListError;
    }

    public void setShoppingListError(String shoppingListError) {
        this.shoppingListError = shoppingListError;
    }

    @Override
    public String toString() {
        return "SLState{" +
                "shoppingList='" + shoppingList + '\'' +
                '}';
    }
}

