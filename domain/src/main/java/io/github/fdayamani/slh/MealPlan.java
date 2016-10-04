package io.github.fdayamani.slh;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;

public class MealPlan {
    private List<Meal> meals = new ArrayList<>();

    public void add(Meal meal) {
        meals.add(meal);
    }

    public Set<String> retrieveShoppingList() {
        Set<String> shoppingList = new HashSet<>();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MealPlan mealPlan = (MealPlan) o;

        return meals != null ? meals.equals(mealPlan.meals) : mealPlan.meals == null;

    }

    @Override
    public int hashCode() {
        return meals != null ? meals.hashCode() : 0;
    }
}
