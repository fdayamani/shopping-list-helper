package io.github.fdayamani.slh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MealPlanTest {
    private MealPlan mealPlan = new MealPlan();

    @Mock
    private Meal coriander;

    @Mock
    private Meal chickenSteak;


    @Test public void
    returnsEmptySet_WhenMealPlannerIsEmpty() {
        Set<String> groceryList = mealPlan.retrieveShoppingList();

        assertThat(groceryList.isEmpty()).isTrue();
    }

    @Test public void
    returnsIngredientList_ForAllMealsInMealPlanner() {
        mealPlan.add(coriander);
        mealPlan.add(chickenSteak);
        when(coriander.getIngredients()).thenReturn(new HashSet<>(Arrays.asList("Coriander")));
        when(chickenSteak.getIngredients()).thenReturn(new HashSet<>(Arrays.asList("Chicken breast", "marinade")));

        Set<String> groceryList = mealPlan.retrieveShoppingList();

        assertThat(groceryList).containsAll(Arrays.asList("coriander", "chicken breast", "marinade"));
    }
}