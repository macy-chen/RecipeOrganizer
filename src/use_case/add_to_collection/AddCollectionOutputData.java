package use_case.add_to_collection;

import entity.Recipe;
import java.util.List;

public class AddCollectionOutputData {
    private final String name;
    private final List<Recipe> collection;
    private boolean useCaseFailed;

    public AddCollectionOutputData(String name, List<Recipe> collection, boolean useCaseFailed) {
        this.name = name;
        this.collection = collection;
        this.useCaseFailed = useCaseFailed;
    }
    public List<Recipe> getCollectionResults() {
        return collection;
    }
}
