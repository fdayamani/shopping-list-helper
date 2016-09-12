package io.github.fdayamani.slh;

import java.util.ArrayList;
import java.util.List;

public class MealPlanner {
    private List<Meal> meals = new ArrayList<>();

    public void add(Meal meal) {
        meals.add(meal);
    }

    public List<Meal> getMeals() {
        return meals;
    }
}
