package io.github.fdayamani.slh;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.github.fdayamani.slh.Meal.Builder.aMeal;

public class FileMealPlannerCreator implements MealPlannerCreator {
    private final String MEAL_PLANNER_PATH;

    public FileMealPlannerCreator(String mealplannerPath) {
        MEAL_PLANNER_PATH = mealplannerPath;
    }

    @Override
    public MealPlanner createMealPlan() throws IOException {
        MealPlanner planner = new MealPlanner();
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

    private void addMealTo(MealPlanner planner, Path file) throws IOException {
        planner.add(createMealFrom(file));
    }

    private Meal createMealFrom(Path file) throws IOException {
        List<String> ingredients = extractIngredientsFrom(file);
        String instructions = extractInstructionsFrom(file);

        return aMeal()
                .withIngredients(ingredients)
                .withInstructions(instructions)
                .build();
    }

    private List<String> extractIngredientsFrom(Path file) throws IOException {
        Optional<String> ingredientLine = Files.readAllLines(file, Charset.forName("ISO-8859-1")).stream()
            .filter(line -> line.contains("Ingredients: "))
            .findFirst();
        String ingredients = getListFrom(ingredientLine);
        return Arrays.asList(ingredients.split(", "));
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
