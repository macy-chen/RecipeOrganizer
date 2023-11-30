package use_case;

public interface SearchOutputBoundary {

    void prepareSuccessView(SearchOutputData results);

    void prepareFailView(String error);
}
