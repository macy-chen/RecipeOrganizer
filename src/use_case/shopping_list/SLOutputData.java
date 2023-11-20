package use_case.shopping_list;

public class  SLOutputData {
    private final String shoppingListString;

    private boolean useCaseFailed;

    public SLOutputData(String sl, boolean useCaseFailed) {
        this.shoppingListString = sl;
        this.useCaseFailed = useCaseFailed;
    }

    public String getShoppingListString() {
        return shoppingListString;
    }
}
