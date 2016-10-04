package io.github.fdayamani.slh;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;

public class MealPlanner {
    private List<Meal> meals = new ArrayList<>();
    private Set<String> shoppingList = new HashSet<>();

    public void add(Meal meal) {
        meals.add(meal);
    }

    public Set<String> retrieveShoppingList() {
        for (Meal meal : meals) {
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
