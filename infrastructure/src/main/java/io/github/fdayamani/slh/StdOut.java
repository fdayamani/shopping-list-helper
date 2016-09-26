package io.github.fdayamani.slh;

import java.util.Set;

public class StdOut implements ShoppingDestination {

    @Override
    public void outputShoppingList(Set<String> groceryList) {
        System.out.println(groceryList.toString());
    }
}
