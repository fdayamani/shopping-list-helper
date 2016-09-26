package io.github.fdayamani.slh;

import java.io.IOException;

public class ShoppingListRunner {
    public static void main(String[] args) throws IOException {
        new ShoppingListRunner().run("/Users/fatemadayamani/Desktop/mealplan");
    }

    private void run(String recipeDirectory) throws IOException {
        ShoppingListGenerator generator = new ShoppingListGeneratorFactory().make(recipeDirectory);

        generator.generate();
    }

    class ShoppingListGeneratorFactory {
        private ShoppingListGenerator make(String recipeDirectory) {
            FileMealPlannerCreator mealPlannerCreator = new FileMealPlannerCreator(recipeDirectory);
            StdOut destination = new StdOut();
            ShoppingList shoppingList = new ShoppingList();

            return new ShoppingListGenerator(mealPlannerCreator, destination, shoppingList);
        }
    }
}
