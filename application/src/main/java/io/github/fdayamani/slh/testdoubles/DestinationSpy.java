package io.github.fdayamani.slh.testdoubles;

import io.github.fdayamani.slh.ShoppingDestination;

import java.util.List;

public class DestinationSpy implements ShoppingDestination {
    private List<String> groceryList;

    @Override
    public void outputShoppingList(List<String> groceryList) {
        this.groceryList = groceryList;
    }

    public List<String> invokedWith() {
        return groceryList;
    }
}
