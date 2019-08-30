package io.github.fdayamani.slh;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static io.github.fdayamani.slh.Meal.Builder.aMeal;
import static org.assertj.core.api.Assertions.assertThat;

public class FileMealPlanCreatorIntegrationTest {
    private static final String DIRECTORY_CONTAINING_RECIPE = "src/test/resources/mealPlanner/";
    private static final Set<String> CHICKEN_STEAK_INGREDIENTS = new HashSet(Arrays.asList("chicken steaks, marinade"));
    private FileMealPlanCreator mealPlanCreator;
    private MealPlan mealPlan;

    @Test public void
    convertFilesInDirectory_ToMealPlan() throws IOException {
        mealPlanCreator = new FileMealPlanCreator(DIRECTORY_CONTAINING_RECIPE);

        mealPlan = mealPlanCreator.createMealPlan();

        assertThat(mealPlan).isEqualTo(expectedMealPlan());
    }

    private MealPlan expectedMealPlan() {
        MealPlan expectedMealPlan = new MealPlan();
        expectedMealPlan.add(aMeal().withIngredients(CHICKEN_STEAK_INGREDIENTS).build());
        return expectedMealPlan;
    }
}