package io.github.fdayamani.slh;

import java.io.IOException;
import java.util.Set;

public class ShoppingListGenerator {
    private final MealPlannerCreator mealplannerCreator;
    private final ShoppingDestination destination;
    private final ShoppingList shoppingList;
    private Set<String> groceryList;

    public ShoppingListGenerator(MealPlannerCreator mealplannerCreator, ShoppingDestination destination, ShoppingList shoppingList) {
        this.mealplannerCreator = mealplannerCreator;
        this.destination = destination;
        this.shoppingList = shoppingList;
    }

    public void generate() throws IOException {
        groceryList = shoppingList.retrieveShoppingListFor(mealPlan());
        destination.outputShoppingList(groceryList);
    }

    private MealPlanner mealPlan() throws IOException {
        return mealplannerCreator.createMealPlan();
    }
}
