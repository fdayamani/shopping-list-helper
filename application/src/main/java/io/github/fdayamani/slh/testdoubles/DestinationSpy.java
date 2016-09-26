package io.github.fdayamani.slh.testdoubles;

import io.github.fdayamani.slh.ShoppingDestination;

import java.util.Set;

public class DestinationSpy implements ShoppingDestination {
    private Set<String> groceryList;

    @Override
    public void outputShoppingList(Set<String> groceryList) {
        this.groceryList = groceryList;
    }

    public Set<String> invokedWith() {
        return groceryList;
    }
}
