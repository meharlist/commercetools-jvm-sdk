package io.sphere.sdk.products.expansion;

import io.sphere.sdk.expansion.ExpansionPathContainer;
import io.sphere.sdk.types.expansion.TypeExpansionModel;

public interface CustomExpansionModel<T> extends ExpansionPathContainer<T> {
    TypeExpansionModel<T> type();
}
