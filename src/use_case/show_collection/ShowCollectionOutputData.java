package use_case.show_collection;

import entity.Recipe;

import java.util.List;

public class ShowCollectionOutputData {
    private final String name;
    private final Float calories;
    private final String culture;

    public ShowCollectionOutputData(String name, Float calories, String culture) {
        this.name = name;
        this.calories = calories;
        this.culture = culture;
    }

}
