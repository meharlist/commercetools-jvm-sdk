package io.sphere.sdk.types.expansion;

import io.sphere.sdk.expansion.ExpansionPathContainer;
import io.sphere.sdk.types.Type;

/**
 DSL class to create expansion path expressions.

 @param <T> the type for which the expansion path is
 */
public interface TypeExpansionModel<T> extends ExpansionPathContainer<T> {

    static TypeExpansionModel<Type> of() {
        return new TypeExpansionModelImpl<>();
    }
}
