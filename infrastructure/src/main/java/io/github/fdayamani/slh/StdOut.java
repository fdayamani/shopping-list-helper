package io.github.fdayamani.slh;

import java.util.List;

public class StdOut implements ShoppingDestination {

    @Override
    public void outputShoppingList(List<String> groceryList) {
        System.out.println(groceryList.toString());
    }
}
