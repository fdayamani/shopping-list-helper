package io.github.fdayamani.slh;

import java.util.List;

public class Meal {
    public List<Ingredient> ingredients;
    private String instructions;

    public static class Builder {
        private Meal meal = new Meal();

        public static Builder aMeal() {
            return new Builder();
        }

        public Builder withIngredients(List<Ingredient> ingredients) {
            meal.ingredients = ingredients;
            return this;
        }

        public Builder withInstructions(String instructions) {
            meal.instructions = instructions;
            return this;
        }

        public Meal build() {
            return meal;
        }
    }
}
