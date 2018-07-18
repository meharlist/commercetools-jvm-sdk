package io.sphere.sdk.shoppinglists.expansion;

import io.sphere.sdk.customers.expansion.CustomerExpansionModel;
import io.sphere.sdk.expansion.ExpansionModelImpl;
import io.sphere.sdk.products.expansion.CustomExpansionModel;
import io.sphere.sdk.products.expansion.CustomExpansionModelImpl;

import java.util.List;

final class ShoppingListExpansionModelImpl<T> extends ExpansionModelImpl<T> implements ShoppingListExpansionModel<T> {
    public ShoppingListExpansionModelImpl(final List<String> parentPath, final String path) {
        super(parentPath, path);
    }

    ShoppingListExpansionModelImpl() {
        super();
    }

    @Override
    public CustomerExpansionModel<T> customer() {
        return CustomerExpansionModel.of(buildPathExpression(), "customer");
    }

    @Override
    public LineItemExpansionModel<T> lineItems() {
        return LineItemExpansionModel.of(buildPathExpression(), "lineItems[*]");
    }

    @Override
    public CustomExpansionModel<T> custom() {
        return new CustomExpansionModelImpl<>(pathExpression(), "custom");
    }

    //TODO: TextLineItem is missing!
}
