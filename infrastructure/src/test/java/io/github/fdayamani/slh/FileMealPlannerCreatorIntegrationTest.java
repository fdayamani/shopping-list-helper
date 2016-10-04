package io.github.fdayamani.slh;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.github.fdayamani.slh.Meal.Builder.aMeal;
import static java.nio.file.Files.write;
import static org.assertj.core.api.Assertions.assertThat;

public class FileMealPlannerCreatorIntegrationTest {
    private static final String MEALPLANNER_PATH = "src/test/resources/mealPlanner/";
    private static final String CHICKEN_STEAK_INGREDIENTS = "chicken steak, marinade";
    private FileMealPlannerCreator mealPlanCreator = new FileMealPlannerCreator(MEALPLANNER_PATH);
    private MealPlan mealPlan;

    @Test public void
    createMealPlanContainingMealInFile() throws IOException {
        mealPlan = mealPlanCreator.createMealPlan();

        List<String> lines = Arrays.asList("Ingredients: " + CHICKEN_STEAK_INGREDIENTS, "Instructions: " + " marinate chicken, cook in oven at 180 for 30 minutes");
        String mealName = "chicken_steak.txt";
        File file = new File(MEALPLANNER_PATH + "/" +  mealName);
        write(Paths.get(file.toURI()), lines, StandardCharsets.UTF_8);

        assertThat(mealPlan).isEqualTo(expectedMealPlan());
    }

    @After
    public void clearDown() throws IOException {
        FileUtils.cleanDirectory(new File(MEALPLANNER_PATH));
    }

    private MealPlan expectedMealPlan() {
        MealPlan expectedMealPlan = new MealPlan();
        Set<String> ingredients = new HashSet(Arrays.asList(CHICKEN_STEAK_INGREDIENTS.split(", ")));
        expectedMealPlan.add(aMeal().withIngredients(ingredients).build());
        return expectedMealPlan;
    }
}