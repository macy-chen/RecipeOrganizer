package use_case;

public interface ShowCollectionOutputBoundary {

    void prepareSuccessView(ShowCollectionOutputData showCollectionOutputData);

    void prepareFailView(String error);
}
