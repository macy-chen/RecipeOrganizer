package use_case.show_collection;

public interface ShowCollectionOutputBoundary {

    void prepareSuccessView(ShowCollectionOutputData showCollectionOutputData);

    void prepareFailView(String error);
}
