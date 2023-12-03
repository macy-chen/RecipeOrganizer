package use_case.search;

public interface SearchOutputBoundary {

    void prepareSuccessView(SearchOutputData results);

    void prepareFailView(String error);
}
