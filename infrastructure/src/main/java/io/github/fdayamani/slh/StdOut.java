package io.github.fdayamani.slh;

import java.util.Set;

public class StdOut implements ShoppingDestination {

    @Override
    public void outputShoppingList(Set<String> groceryList) {
        groceryList.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .forEach(ingredient -> System.out.println(ingredient));
    }
}
