package io.sphere.sdk.products.expansion;

import io.sphere.sdk.expansion.ExpansionModelImpl;

import java.util.List;

public final class AssetExpansionModelImpl<T> extends ExpansionModelImpl<T> implements AssetExpansionModel<T> {
    public AssetExpansionModelImpl(final List<String> parentPath, final String path) {
        super(parentPath, path);
    }
}
