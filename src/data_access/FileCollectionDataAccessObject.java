package data_access;

import entity.Recipe;
import use_case.ShowCollectionCollectionDataAccessInterface;
import use_case.ShowCollectionInputData;
import use_case.add_to_collection.AddCollectionCollectionDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;

import static java.util.Collections.emptyList;

public class FileCollectionDataAccessObject implements AddCollectionCollectionDataAccessInterface, ShowCollectionCollectionDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Recipe> collection = new HashMap<>();

    public FileCollectionDataAccessObject(String csvPath) throws IOException {
        csvFile = new File(csvPath);
        headers.put("name", 0);
        headers.put("culture", 1);
        headers.put("calories", 2);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("name");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String name = String.valueOf(col[headers.get("name")]);
                    String culture = String.valueOf(col[headers.get("culture")]);
                    String calories = String.valueOf(col[headers.get("calories")]);
                }
            }
        }
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

    public List<Recipe> load(ShowCollectionInputData showCollectionInputData) {
        List<Recipe> recipeList = new ArrayList<>();
        List<String> headers;

        try (BufferedReader reader = new BufferedReader(new FileReader(showCollectionInputData.getFilePath()))) {
            String headersLine = reader.readLine();
            headers = Arrays.asList(headersLine.split(","));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                recipeList.add(new Recipe(values[0], null, null, Float.parseFloat(values[2]), values[1], 0, ""));
            }

            reader.close();

            return recipeList;

        } catch (IOException e) {
            throw new RuntimeException("Error reading the CSV file", e);
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
