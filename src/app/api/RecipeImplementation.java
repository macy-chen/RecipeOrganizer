package app.api;

import java.security.Key;
import java.util.ArrayList;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import entity.Recipe;
import entity.Ingredient;
import app.api.IngredientImplementation;

public class RecipeImplementation implements recipeAPI {

    private static final String API_TOKEN = System.getenv("API_TOKEN");
    private static final String APP_ID = System.getenv("APP_ID");

    public static String getApiToken() {
        return API_TOKEN;
    }

    public static String getAppId() {
        return APP_ID;
    }

    @Override
    public ArrayList<Recipe> getResults(String keyword) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("q", keyword);
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url("https://api.edamam.com/api/recipes/v2/?type=public&q=" + keyword + "&app_id=" + getAppId() + "&app_key=" + getApiToken())
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONArray hits = responseBody.getJSONArray("hits");
                ArrayList<Recipe> recipes = new ArrayList<Recipe>();
                if (hits.isEmpty()) {
                    return recipes;
                }
                for (int i = 0; i < 5; i++) {
                    JSONObject curr = hits.getJSONObject(i);
                    JSONObject currRecipe = curr.getJSONObject("recipe");
                    String name = currRecipe.getString("label");
                    JSONArray ingredientArray = currRecipe.getJSONArray("ingredients");
                    ArrayList<Ingredient> recipeIngredients = ingredientHelper(ingredientArray);
                    String url = currRecipe.getString("url");
                    Float calories = currRecipe.getFloat("calories");
                    JSONArray cuisine = currRecipe.getJSONArray("cuisineType");
                    String culture = cuisine.getString(0);
                    ArrayList<String> recipeNutrients = new ArrayList<>(); // remove later
                    JSONObject nutrients = currRecipe.getJSONObject("totalNutrients"); //remove later
                    // JSONObject nutrientObject = currRecipe.getJSONObject("totalNutrients");
                    // ArrayList<Nutrient> nutrients = nutrientHelper(nutrientObject);
                    for (Iterator<String> it = nutrients.keys(); it.hasNext(); ) { // remove later
                        String key = it.next();
                        recipeNutrients.add(nutrients.getJSONObject(key).getString("label"));
                    }
                    Integer portion = currRecipe.getInt("yield");
                    Recipe recipe = new Recipe(name, recipeIngredients, recipeNutrients, calories, culture, portion, url); // change later
                    recipes.add(recipe);
                }

                return recipes;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Ingredient> ingredientHelper(JSONArray ingredientsArray) {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        for (int j = 0; j < ingredientsArray.length(); j++) {
            JSONObject currIngredient = ingredientsArray.getJSONObject(j);
            Ingredient newIngredient;
            String name = currIngredient.getString("food");
            Float amount = currIngredient.getFloat("quantity");
            String measurement = null; // the measurement of some things like salt/pepper is null
            if (!(amount == 0)) { // amount of salt, pepper, etc. is 0, so use if statement to avoid trying to use getString() null
                measurement = currIngredient.getString("measure");
            }
            String category = currIngredient.getString("foodCategory"); // might use?
            IngredientImplementation ingCaloriesNutrients = new IngredientImplementation();
            //Float calories = ingCaloriesNutrients.getIngredientCalories(currIngredient.getString("text")); // might use?
            newIngredient = new Ingredient(name, amount, measurement);
            ingredients.add(newIngredient);
        }
        return ingredients;
    }

/*    private ArrayList<Nutrient> nutrientHelper(JSONObject nutrientsObject) {
        ArrayList<Nutrient> nutrients = new ArrayList<Nutrient>();
        for (Iterator<String> it = nutrientsObject.keys(); it.hasNext(); ) {
            String key = it.next();
            JSONObject currNutrient = nutrientsObject.getJSONObject(key);
            Nutrient newNutrient;
            String name = currNutrient.getString("label");
            Float amount = currNutrient.getFloat("quantity");
            String measure = currNutrient.getString("unit");
            newNutrient = new Nutrient(name, amount, measure);
            nutrients.add(newNutrient);
        }
        return nutrients;
    }*/


    public static void main(String[] args) {
        RecipeImplementation imp = new RecipeImplementation();
        ArrayList<Recipe> recipes = imp.getResults("chicken");
        System.out.println(recipes);
        System.out.println(recipes.get(0).getCalories());
        System.out.println(recipes.get(0).getIngredients());
        System.out.println(recipes.get(0).getCulture());
        System.out.println(recipes.get(0).getUrl());
        System.out.println(recipes.get(0).getNutrients());
        System.out.println(recipes.get(0).getPortion());
    }

}