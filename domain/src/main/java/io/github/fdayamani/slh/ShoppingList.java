package io.github.fdayamani.slh;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptySet;

public class ShoppingList {
    private Set<String> shoppingList = new HashSet<>();

    public Set<String> retrieveShoppingListFor(MealPlanner mealPlanner) {
        for (Meal meal : mealPlanner.getMeals()) {
            shoppingList.addAll(meal.getIngredients());
        }
        return shoppingList == null ? emptySet() : shoppingList;
    }
}
