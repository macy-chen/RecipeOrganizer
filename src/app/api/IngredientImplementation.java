package app.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class IngredientImplementation implements ingredientAPI {

    private static final String API_TOKEN2 = System.getenv("API_TOKEN2");
    private static final String APP_ID2 = System.getenv("APP_ID2");

    public static String getApiToken() {
        return API_TOKEN2;
    }

    public static String getAppId() {
        return APP_ID2;
    }
    @Override
    public Float getIngredientCalories(String ingredientName) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.edamam.com/api/nutrition-data?app_id=" + getAppId() + "&app_key=" + getApiToken() +
                        "&nutrition-type=cooking&ingr=" + ingredientName)
                .addHeader("Authorization", API_TOKEN2)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                Float calories = responseBody.getFloat("calories");
                return calories;

            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


public static void main(String[] args) {
    IngredientImplementation imp = new IngredientImplementation();
    Float calories = imp.getIngredientCalories("5 cloves garlic, peeled");
    Float calories2 = imp.getIngredientCalories("2 large russet potatoes, peeled and cut into chunks");
    System.out.println(calories);
    System.out.println(calories2);
}
}
