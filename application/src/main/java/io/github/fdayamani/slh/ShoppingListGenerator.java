package io.github.fdayamani.slh;

import java.io.IOException;
import java.util.Set;

public class ShoppingListGenerator {
    private final MealPlannerCreator mealplannerCreator;
    private final ShoppingDestination destination;
    private Set<String> groceryList;

    public ShoppingListGenerator(MealPlannerCreator mealplannerCreator, ShoppingDestination destination) {
        this.mealplannerCreator = mealplannerCreator;
        this.destination = destination;
    }

    public void generate() throws IOException {
        groceryList = mealPlan().retrieveShoppingList();
        destination.outputShoppingList(groceryList);
    }

    private MealPlanner mealPlan() throws IOException {
        return mealplannerCreator.createMealPlan();
    }
}
