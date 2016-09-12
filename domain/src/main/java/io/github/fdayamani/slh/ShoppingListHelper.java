package io.github.fdayamani.slh;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class ShoppingListHelper {
    public static List<String> retrieveShoppingListFor(MealPlanner mealPlanner) {
        List<String> shoppingList = new ArrayList<>();
        for (Meal meal : mealPlanner.getMeals()) {
            shoppingList.addAll(meal.getIngredients());
        }
        return shoppingList == null ? emptyList() : shoppingList;
    }
}
