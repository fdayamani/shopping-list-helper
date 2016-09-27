package io.github.fdayamani.slh;

import java.util.Set;
import java.util.TreeSet;

public class StdOut implements ShoppingDestination {

    @Override
    public void outputShoppingList(Set<String> groceryList) {
        System.out.println(sortedOutput(groceryList));
    }

    private String sortedOutput(Set<String> groceryList) {
        return new TreeSet(groceryList).toString().replaceAll(",", "\n");
    }
}
