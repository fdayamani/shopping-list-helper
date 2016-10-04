package io.github.fdayamani.slh;

import java.io.IOException;

public class ShoppingListRunner {
    public static void main(String[] args) throws IOException {
        new ShoppingListRunner().run(args[0]);
    }

    private void run(String recipeDirectory) throws IOException {
        ShoppingListGenerator generator = new ShoppingListGeneratorFactory().make(recipeDirectory);

        generator.generate();
    }

    class ShoppingListGeneratorFactory {
        private ShoppingListGenerator make(String recipeDirectory) {
            FileMealPlannerCreator mealPlannerCreator = new FileMealPlannerCreator(recipeDirectory);
            StdOut destination = new StdOut();

            return new ShoppingListGenerator(mealPlannerCreator, destination);
        }
    }
}
