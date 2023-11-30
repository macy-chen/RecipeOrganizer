package use_case;

import entity.RecipeCollection;

public class ShowCollectionInputData {

    final private String filePath;

    public ShowCollectionInputData() {
        this.filePath = "./recipe";
    }

    public String getFilePath() {return filePath;}

}
