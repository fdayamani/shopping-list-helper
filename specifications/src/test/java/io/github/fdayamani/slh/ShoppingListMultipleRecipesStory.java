package io.github.fdayamani.slh;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import java.util.*;

import static io.github.fdayamani.slh.LightweightTestEmbedder.aLightweightTestRunnerWithStepsFrom;
import static io.github.fdayamani.slh.Meal.Builder.aMeal;
import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingListMultipleRecipesStory {
        private MealPlanner mealPlanner = new MealPlanner();
        private Set<String> groceryList = new HashSet<>();

        @Test
        public void verifyShoppingListStory() throws Exception {
            aLightweightTestRunnerWithStepsFrom(this)
                    .withStory("stories/shopping_list_multiple_recipes.story")
                    .run();
        }

        @Given("that my meal planner contains $meals")
        public void mealPlannerContains(String meals) {
            Set<String> fajitaIngredients = new HashSet<>(Arrays.asList(
                    "chicken",
                    "fajita mix",
                    "onions",
                    "peppers",
                    "mushrooms",
                    "wraps"
            ));
            Set<String> pizzaIngredients = new HashSet<>(Arrays.asList(
                    "pizza bases",
                    "tomato paste",
                    "onions",
                    "peppers",
                    "mushrooms",
                    "cheese"
            ));
            mealPlanner.add(
                    aMeal()
                            .withIngredients(fajitaIngredients)
                            .withInstructions("it's easy, figure it out")
                            .build());
            mealPlanner.add(
                    aMeal()
                            .withIngredients(pizzaIngredients)
                            .withInstructions("it's easy, figure it out")
                            .build());
        }

        @When("the shopping list helper is invoked")
        public void invokeShoppingListHelper() {
            groceryList = mealPlanner.retrieveShoppingList();
        }

        @Then("the shopping list contains $ingredients once only")
        public void assertListIsExpectedListOfIngredients(String ingredients) {
            List<String> expectedShoppingList = new ArrayList<>();
            for (String ingredient : ingredients.split(", ")) {
                expectedShoppingList.add(ingredient);
            }

            assertThat(expectedShoppingList.containsAll(groceryList)).isTrue();
            assertThat(groceryList.size()).isEqualTo(expectedShoppingList.size());
        }
    }