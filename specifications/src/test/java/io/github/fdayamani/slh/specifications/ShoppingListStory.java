package io.github.fdayamani.slh.specifications;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import static io.github.fdayamani.slh.specifications.LightweightTestEmbedder.aLightweightTestRunnerWithStepsFrom;

public class ShoppingListStory {

    @Test
    public void verifyShoppingListHelperStory() throws Exception {
        aLightweightTestRunnerWithStepsFrom(this)
                .withStory("stories/shopping_list.story")
                .run();
    }

    @Given("that I want to make $meal")
    public void mealPlannerContains(String meal) {

    }

    @When("I invoke the shopping list helper")
    public void invokeShoppingListHelper() {

    }

    @Then("the final list is $ingredients")
    public void assertListIsExpectedListOfIngredients() {

    }
}
