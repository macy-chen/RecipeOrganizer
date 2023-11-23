package use_case.add_to_collection;
import entity.Recipe;
import java.util.List;


public interface AddCollectionCollectionDataAccessInterface {
    boolean existsByName(String identifier);

    void save(Recipe recipe);

    List<Recipe> getAll();
}
