package io.sphere.sdk.shoppinglists.expansion;

import io.sphere.sdk.customers.expansion.CustomerExpansionModel;
import io.sphere.sdk.expansion.ExpansionPathContainer;
import io.sphere.sdk.products.expansion.CustomExpansionModel;
import io.sphere.sdk.shoppinglists.ShoppingList;

public interface ShoppingListExpansionModel<T> extends ExpansionPathContainer<T> {
    CustomerExpansionModel<T> customer();

    LineItemExpansionModel<T> lineItems();

    CustomExpansionModel<T> custom();

    static ShoppingListExpansionModel<ShoppingList> of() {
        return new ShoppingListExpansionModelImpl<>();
    }
}
