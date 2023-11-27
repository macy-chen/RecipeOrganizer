package app.api;

import org.json.JSONException;

public interface ingredientAPI {
    Float getIngredientCalories(String ingredientName) throws JSONException;

/*
    ArrayList<Nutrient> getIngredientNutrients(String ingredientName);
*/
}
