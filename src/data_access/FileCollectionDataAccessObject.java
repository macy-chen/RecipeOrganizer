package data_access;

import entity.Recipe;
import use_case.add_to_collection.AddCollectionCollectionDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileCollectionDataAccessObject implements AddCollectionCollectionDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Recipe> collection = new HashMap<>();

    public FileCollectionDataAccessObject(String csvPath) throws IOException {
        csvFile = new File(csvPath);
        headers.put("name", 0);
        headers.put("culture", 1);
        headers.put("calories", 2);

        save();
    }

    @Override
    public void save(Recipe recipe) {
        collection.put(recipe.getName(), recipe);
        this.save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Recipe recipe : collection.values()) {
                String line = String.format("%s,%s,%s",
                        recipe.getName(), recipe.getCulture(), recipe.getCalories());
                System.out.println(recipe.getCulture());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Recipe> getAll() {
        List<Recipe> recipeslist = new ArrayList<Recipe>();
        for (Recipe recipe : collection.values()) {
            recipeslist.add(recipe);
        }
        return recipeslist;
    }

    /**
     * Return whether a recipe exists with name identifier.
     * @param identifier the username to check.
     * @return whether a user exists with name identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return collection.containsKey(identifier);
    }
}
