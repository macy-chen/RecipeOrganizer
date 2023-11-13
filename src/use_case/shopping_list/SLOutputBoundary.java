package use_case.shopping_list;

public interface SLOutputBoundary {

    void prepareSuccessView(SLOutputData slOutputData);

    void prepareFailView(String error);
}
