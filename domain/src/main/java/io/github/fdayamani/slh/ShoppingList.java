package io.github.fdayamani.slh;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class ShoppingList {
    private List<String> shoppingList = new ArrayList<>();

    public List<String> retrieveShoppingListFor(MealPlanner mealPlanner) {
        for (Meal meal : mealPlanner.getMeals()) {
            shoppingList.addAll(meal.getIngredients());
        }
        return shoppingList == null ? emptyList() : shoppingList;
    }
}
