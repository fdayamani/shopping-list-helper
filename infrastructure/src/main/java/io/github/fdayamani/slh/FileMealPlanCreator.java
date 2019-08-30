package io.github.fdayamani.slh;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static io.github.fdayamani.slh.Meal.Builder.aMeal;

public class FileMealPlanCreator implements MealPlannerCreator {
    private final String MEAL_PLANNER_PATH;

    public FileMealPlanCreator(String mealplannerPath) {
        MEAL_PLANNER_PATH = mealplannerPath;
    }

    @Override
    public MealPlan createMealPlan() throws IOException {
        MealPlan planner = new MealPlan();
        Files.walk(Paths.get(MEAL_PLANNER_PATH)).forEach(file -> {
            try {
                if(file.toFile().isFile() && !file.toFile().isHidden()) {
                    addMealTo(planner, file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return planner;
    }

    private void addMealTo(MealPlan planner, Path file) throws IOException {
        planner.add(createMealFrom(file));
    }

    private Meal createMealFrom(Path file) throws IOException {
        Set<String> ingredients = extractIngredientsFrom(file);
        String instructions = extractInstructionsFrom(file);

        return aMeal()
                .withIngredients(ingredients)
                .withInstructions(instructions)
                .build();
    }

    private Set<String> extractIngredientsFrom(Path file) throws IOException {
        Optional<String> ingredientLine = Files.readAllLines(file, Charset.forName("ISO-8859-1")).stream()
            .map(String::toLowerCase)
            .filter(line -> line.contains("ingredients: "))
            .findFirst();
        String ingredients = getListFrom(ingredientLine);
        return new HashSet<>(Arrays.asList(ingredients.split(", ")));
    }

    private String extractInstructionsFrom(Path file) throws IOException {
        Optional<String> instructionLine = Files.readAllLines(file,  Charset.forName("ISO-8859-1")).stream()
                .filter(line -> line.contains("Instructions: "))
                .findFirst();
        String instructions = getListFrom(instructionLine);
        return instructions;
    }

    private String getListFrom(Optional<String> originalLine) {
        String line = originalLine.orElse("  ");
        return line.contains(":") ? line.substring(line.indexOf(":") + 2) : "";
    }
}
