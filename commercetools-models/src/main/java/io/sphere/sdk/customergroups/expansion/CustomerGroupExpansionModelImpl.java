package io.sphere.sdk.customergroups.expansion;

import io.sphere.sdk.expansion.ExpansionModelImpl;
import io.sphere.sdk.products.expansion.CustomExpansionModel;
import io.sphere.sdk.products.expansion.CustomExpansionModelImpl;

import java.util.List;

final class CustomerGroupExpansionModelImpl<T> extends ExpansionModelImpl<T> implements CustomerGroupExpansionModel<T> {
    CustomerGroupExpansionModelImpl(final List<String> parentPath, final String path) {
        super(parentPath, path);
    }

    CustomerGroupExpansionModelImpl() {
        super();
    }

    @Override
    public CustomExpansionModel<T> custom() {
        return new CustomExpansionModelImpl<>(pathExpression(), "custom");
    }
}
