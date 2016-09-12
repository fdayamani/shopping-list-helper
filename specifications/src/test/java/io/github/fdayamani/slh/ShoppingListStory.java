package io.github.fdayamani.slh;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.fdayamani.slh.LightweightTestEmbedder.aLightweightTestRunnerWithStepsFrom;
import static io.github.fdayamani.slh.Meal.Builder.aMeal;
import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingListStory {

    private ShoppingListHelper shoppingListHelper = new ShoppingListHelper();
    private MealPlanner mealPlanner = new MealPlanner();
    private List<Ingredient> shoppingList;

    @Test
    public void verifyShoppingListHelperStory() throws Exception {
        aLightweightTestRunnerWithStepsFrom(this)
                .withStory("stories/shopping_list.story")
                .run();
    }

    @Given("that I want to make $meal")
    public void mealPlannerContains(String meal) {
        List<Ingredient> chickenBurgerIngredients = Arrays.asList(
                new Ingredient("chicken steaks"),
                new Ingredient("Nando's marinade"),
                new Ingredient("red chillies"),
                new Ingredient("lime"),
                new Ingredient("olive oil")
                );
        mealPlanner.add(
                aMeal()
                    .withIngredients(chickenBurgerIngredients)
                    .withInstructions("marinate overnight then cook on griddle pan. Use extra mixture to baste")
                    .build());
    }

    @When("I invoke the shopping list helper")
    public void invokeShoppingListHelper() {
        shoppingList = shoppingListHelper.retrieveShoppingListFor(mealPlanner);
    }

    @Then("the final list is $ingredients")
    public void assertListIsExpectedListOfIngredients(String ingredients) {
        List<Ingredient> expectedShoppingList = new ArrayList<>();
        for (String ingredient : ingredients.split(", ")) {
            expectedShoppingList.add(new Ingredient(ingredient));
        }

        assertThat(shoppingList).isEqualTo(expectedShoppingList);
    }
}
