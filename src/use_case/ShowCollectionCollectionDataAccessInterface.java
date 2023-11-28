package use_case;
import entity.Recipe;
import java.util.List;


public interface ShowCollectionCollectionDataAccessInterface {

    List<Recipe> load(ShowCollectionInputData showCollectionInputData);

    List<Recipe> getAll();
}
