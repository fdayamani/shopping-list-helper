package io.github.fdayamani.slh;

import io.github.fdayamani.slh.testdoubles.DestinationSpy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.github.fdayamani.slh.Meal.Builder.aMeal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShoppingListGeneratorTest {
    public static final Set<String> INGREDIENTS = new HashSet<>(Arrays.asList("chicken", "marinade"));
    private MealPlannerCreator mealPlannerCreator = mock(MealPlannerCreator.class);
    private DestinationSpy destination = new DestinationSpy();

    private ShoppingListGenerator generator = new ShoppingListGenerator(mealPlannerCreator, destination);
    private MealPlanner planner = mock(MealPlanner.class);


    @Before public void setUp() throws IOException {
        givenThatMealPlannerContainsChickenSteak();
        when(mealPlannerCreator.createMealPlan()).thenReturn(planner);
    }

    @Test public void
    generatesShoppingList_WithCorrectIngredients() throws IOException {
        generator.generate();

        verify(planner).retrieveShoppingList();
    }

    @Test public void
    invokesDestination_WithCorrectGroceryList() throws IOException {
        when(planner.retrieveShoppingList()).thenReturn(INGREDIENTS);

        generator.generate();

        assertThat(destination.invokedWith()).isEqualTo(INGREDIENTS);
    }

    private void givenThatMealPlannerContainsChickenSteak() {
        Meal meal = aMeal()
                .withIngredients(INGREDIENTS)
                .withInstructions("")
                .build();
        planner.add(meal);
    }

}