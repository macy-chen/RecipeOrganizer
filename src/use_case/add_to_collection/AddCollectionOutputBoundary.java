package use_case.add_to_collection;

public interface AddCollectionOutputBoundary {
    void prepareSuccessView(AddCollectionOutputData results);

    void prepareFailView(String error);
}
