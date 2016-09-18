package io.github.fdayamani.slh;

import io.github.fdayamani.slh.testdoubles.DestinationSpy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static io.github.fdayamani.slh.Meal.Builder.aMeal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShoppingListGeneratorTest {
    public static final List<String> INGREDIENTS = Arrays.asList("chicken", "marinade");
    private MealPlannerCreator mealPlannerCreator = mock(MealPlannerCreator.class);
    private ShoppingList shoppingList = mock(ShoppingList.class);
    private DestinationSpy destination = new DestinationSpy();

    private ShoppingListGenerator generator = new ShoppingListGenerator(mealPlannerCreator, destination, shoppingList);
    private MealPlanner planner = new MealPlanner();

    ArgumentCaptor<MealPlanner> captor = ArgumentCaptor.forClass(MealPlanner.class);

    @Before public void setUp() {
        givenThatMealPlannerContainsChickenSteak();
        when(mealPlannerCreator.createMealPlan()).thenReturn(planner);
    }

    @Test public void
    generatesShoppingList_WithCorrectIngredients() {
        generator.generate();

        verify(shoppingList).retrieveShoppingListFor(captor.capture());
        assertThat(captor.getValue()).isEqualTo(planner);
    }

    @Test public void
    invokesDestination_WithCorrectGroceryList() {
        when(shoppingList.retrieveShoppingListFor(planner)).thenReturn(INGREDIENTS);

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