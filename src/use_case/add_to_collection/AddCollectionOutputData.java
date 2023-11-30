package use_case.add_to_collection;

import entity.Recipe;
import java.util.List;

public class AddCollectionOutputData {
    private final List<Recipe> collection;
    private boolean useCaseFailed;

    public AddCollectionOutputData(List<Recipe> collection, boolean useCaseFailed) {
        this.collection = collection;
        this.useCaseFailed = useCaseFailed;
    }
    public List<Recipe> getCollectionResults() {
        return collection;
    }
}
