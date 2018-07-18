package io.sphere.sdk.customergroups.expansion;

import io.sphere.sdk.customergroups.CustomerGroup;
import io.sphere.sdk.expansion.ExpansionPathContainer;
import io.sphere.sdk.products.expansion.CustomExpansionModel;

import java.util.List;

/**
 DSL class to create expansion path expressions.

 @param <T> the type for which the expansion path is
 */
public interface CustomerGroupExpansionModel<T> extends ExpansionPathContainer<T> {
    CustomExpansionModel<T> custom();

    static CustomerGroupExpansionModel<CustomerGroup> of() {
        return new CustomerGroupExpansionModelImpl<>();
    }

    static <T> CustomerGroupExpansionModel<T> of(final List<String> parentPath, final String path) {
        return new CustomerGroupExpansionModelImpl<>(parentPath, path);
    }
}
