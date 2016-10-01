package io.github.fdayamani.slh;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;

public class ShoppingList {
    private Set<String> shoppingList = new HashSet<>();

    public Set<String> retrieveShoppingListFor(MealPlanner mealPlanner) {
        for (Meal meal : mealPlanner.getMeals()) {
            shoppingList.addAll(ingredientsOf(meal));
        }
        return shoppingList == null ? emptySet() : shoppingList;
    }

    private List<String> ingredientsOf(Meal meal) {
        return meal.getIngredients()
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
