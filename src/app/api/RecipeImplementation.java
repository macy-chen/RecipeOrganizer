package app.api;

import java.util.ArrayList;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Arrays;

public class RecipeImplementation implements recipeAPI {

    private static final String API_URL = "https://api.edamam.com/api/recipes/v2";

    private static final String API_TOKEN = System.getenv("API_TOKEN");
    private static final String APP_ID = System.getenv("APP_ID");

    public static String getApiToken() {
        return API_TOKEN;
    }

    public static String getAppId() { return APP_ID; }

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
                for (int i = 0; i < hits.length(); i++) {
                    JSONObject curr = hits.getJSONObject(i);
                    JSONObject currRecipe = curr.getJSONObject("recipe");
                    String name = currRecipe.getString("label");
                    JSONArray ingredients = currRecipe.getJSONArray("ingredientLines");
                    String url = currRecipe.getString("url");
                    ArrayList<String> recipeIngredients = new ArrayList<>();
                    for (int j = 0; j < ingredients.length(); j++)
                        recipeIngredients.add(ingredients.getString(j));
                    Float calories = currRecipe.getFloat("calories");
                    JSONArray cuisine = currRecipe.getJSONArray("cuisineType");
                    ArrayList<String> recipeCulture = new ArrayList<>();
                    for (int k = 0; k < cuisine.length(); k++)
                        recipeCulture.add(cuisine.getString(k));
                    Recipe recipe = new Recipe(name, recipeIngredients, "", calories, recipeCulture, "1", url);
                    recipes.add(recipe);
                }

                return recipes;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Recipe selectRecipe() {

        return null;
    }

    public static void main(String[] args) {
        RecipeImplementation imp = new RecipeImplementation();
        ArrayList<Recipe> recipes = imp.getResults("chicken");
        System.out.println(recipes);
        System.out.println(recipes.get(0).calories);
        System.out.println(recipes.get(0).ingredients);
        System.out.println(recipes.get(0).culture);
        System.out.println(recipes.get(0).url);
    }

}