package io.github.fdayamani.slh;

import io.github.fdayamani.slh.testdoubles.DestinationSpy;
import org.apache.commons.io.FileUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static io.github.fdayamani.slh.LightweightTestEmbedder.aLightweightTestRunnerWithStepsFrom;
import static java.nio.file.Files.write;
import static org.assertj.core.api.Assertions.assertThat;

public class RecipeConvertedToMealStory {

    public static final String MEALPLANNER_PATH = "src/test/resources/mealPlanner/";
    private String mealName;

    FileMealPlannerCreator mealPlanner = new FileMealPlannerCreator(MEALPLANNER_PATH);
    DestinationSpy destination = new DestinationSpy();
    ShoppingList shoppingList = new ShoppingList();

    ShoppingListGenerator generator = new ShoppingListGenerator(mealPlanner, destination, shoppingList);

    @Test
    public void verifyShoppingListHelperStory() throws Exception {
        aLightweightTestRunnerWithStepsFrom(this)
                .withStory("stories/recipe_converted_to_meal.story")
                .run();
    }

    @Given("the ingredients for $meal are $ingredients")
    public void createRecipeFileWith(String meal, String ingredients) throws IOException {
        List<String> lines = Arrays.asList("Ingredients: " + ingredients, "Instructions: " + " marinate chicken, cook in oven at 180 for 30 minutes");
        mealName = meal.replaceAll(" ", "_") + ".txt";
        File file = new File(MEALPLANNER_PATH + "/" +  mealName);
        write(Paths.get(file.toURI()), lines, StandardCharsets.UTF_8);
    }

    @When("the shopping list is generated")
    public void generateShoppingList() throws IOException {
        generator.generate();
    }

    @Then("the final shopping list contains $ingredients")
    public void assertThatShoppingListContains(String ingredients) {
        List<String> expectedIngredients = Arrays.asList(ingredients.split(", "));

        assertThat(capturedShoppingList().containsAll(expectedIngredients)).isTrue();
    }

    private Set<String> capturedShoppingList() {
        return destination.invokedWith();
    }

    @After
    public void clearDown() throws IOException {
        FileUtils.cleanDirectory(new File(MEALPLANNER_PATH));
    }

}
