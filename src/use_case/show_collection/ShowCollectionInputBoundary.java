package use_case.show_collection;

import entity.Recipe;

import java.util.List;

public interface ShowCollectionInputBoundary {

    List<Recipe> execute();
}
