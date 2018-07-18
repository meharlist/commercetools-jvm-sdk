package io.sphere.sdk.types.expansion;

import io.sphere.sdk.expansion.ExpansionModelImpl;

import java.util.List;

public final class TypeExpansionModelImpl<T> extends ExpansionModelImpl<T> implements TypeExpansionModel<T> {

    public TypeExpansionModelImpl(final List<String> parentPath, final String path) {
        super(parentPath, path);
    }

    TypeExpansionModelImpl(final String parentPath, final String path) {
        super(parentPath, path);
    }

    TypeExpansionModelImpl() {
        super();
    }
}
