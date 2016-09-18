package io.github.fdayamani.slh;

import java.util.List;

public class ShoppingListGenerator {
    private final MealPlannerCreator mealplannerCreator;
    private final ShoppingDestination destination;
    private final ShoppingList shoppingList;
    private List<String> groceryList;

    public ShoppingListGenerator(MealPlannerCreator mealplannerCreator, ShoppingDestination destination, ShoppingList shoppingList) {
        this.mealplannerCreator = mealplannerCreator;
        this.destination = destination;
        this.shoppingList = shoppingList;
    }

    public void generate() {
        groceryList = shoppingList.retrieveShoppingListFor(mealPlan());
        destination.outputShoppingList(groceryList);
    }

    private MealPlanner mealPlan() {
        return mealplannerCreator.createMealPlan();
    }
}
