package io.sphere.sdk.products.expansion;

import io.sphere.sdk.expansion.ExpansionModelImpl;
import io.sphere.sdk.types.expansion.TypeExpansionModel;
import io.sphere.sdk.types.expansion.TypeExpansionModelImpl;

import java.util.List;

public final class CustomExpansionModelImpl<T> extends ExpansionModelImpl<T> implements CustomExpansionModel<T> {
    public CustomExpansionModelImpl(final List<String> parentPath, final String path) {
        super(parentPath, path);
    }

    @Override
    public TypeExpansionModel<T> type() {
        return new TypeExpansionModelImpl<>(pathExpression(), "type");
    }
}
