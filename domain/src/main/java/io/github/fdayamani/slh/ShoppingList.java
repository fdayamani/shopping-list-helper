package io.github.fdayamani.slh;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class ShoppingList {
    private MealPlanner mealPlanner;
    private List<String> shoppingList = new ArrayList<>();

    public ShoppingList(MealPlanner mealPlanner) {
        this.mealPlanner = mealPlanner;
    }

    public List<String> retrieveShoppingList() {
        for (Meal meal : mealPlanner.getMeals()) {
            shoppingList.addAll(meal.getIngredients());
        }
        return shoppingList == null ? emptyList() : shoppingList;
    }
}
