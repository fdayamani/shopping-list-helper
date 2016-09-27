package io.github.fdayamani.slh;

import java.util.Set;

public class Meal {
    public Set<String> ingredients;
    private String instructions;

    public Set<String> getIngredients() {
        return ingredients;
    }

    public static class Builder {
        private Meal meal = new Meal();

        public static Builder aMeal() {
            return new Builder();
        }

        public Builder withIngredients(Set<String> ingredients) {
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
