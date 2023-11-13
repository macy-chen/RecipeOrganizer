package use_case.shopping_list;

public interface SLOutputBoundary {

    void prepareSuccessView(SLOutputData shoppingList);

    void prepareFailView(String error);
}
