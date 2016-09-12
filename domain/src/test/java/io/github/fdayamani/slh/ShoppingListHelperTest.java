package io.github.fdayamani.slh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static io.github.fdayamani.slh.ShoppingListHelper.retrieveShoppingListFor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListHelperTest {
    private MealPlanner mealPlanner = new MealPlanner();

    @Mock
    private Meal coriander;

    @Mock
    private Meal chickenSteak;


    @Test public void
    returnsEmptyList_WhenMealPlannerIsEmpty() {
        List<String> shoppingList = retrieveShoppingListFor(mealPlanner);

        assertThat(shoppingList.isEmpty()).isTrue();
    }

    @Test public void
    returnsIngredientList_ForAllMealsInMealPlanner() {
        mealPlanner.add(coriander);
        mealPlanner.add(chickenSteak);
        when(coriander.getIngredients()).thenReturn(Arrays.asList("Coriander"));
        when(chickenSteak.getIngredients()).thenReturn(Arrays.asList("Chicken breast", "marinade"));

        List<String> shoppingList = retrieveShoppingListFor(mealPlanner);

        assertThat(shoppingList).isEqualTo(Arrays.asList("Coriander", "Chicken breast", "marinade"));
    }
}