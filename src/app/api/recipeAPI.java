package app.api;

import java.util.ArrayList;
import org.json.JSONException;

public interface recipeAPI {

    ArrayList<Recipe> getResults(String keyword) throws JSONException;

    Recipe selectRecipe();
}
