package io.sphere.sdk.categories.expansion;

import io.sphere.sdk.expansion.ExpansionModelImpl;
import io.sphere.sdk.products.expansion.AssetExpansionModel;
import io.sphere.sdk.products.expansion.AssetExpansionModelImpl;

import java.util.List;

final class CategoryExpansionModelImpl<T> extends ExpansionModelImpl<T> implements CategoryExpansionModel<T> {
    CategoryExpansionModelImpl(final List<String> parentPath, final String path) {
        super(parentPath, path);
    }

    CategoryExpansionModelImpl() {
        super();
    }

    @Override
    public CategoryExpansionModel<T> ancestors(final int index) {
        return new CategoryExpansionModelImpl<>(pathExpression(), collection("ancestors", index));
    }

    @Override
    public CategoryExpansionModel<T> ancestors() {
        return new CategoryExpansionModelImpl<>(pathExpression(), "ancestors[*]");
    }

    @Override
    public CategoryExpansionModel<T> parent() {
        return new CategoryExpansionModelImpl<>(pathExpression(), "parent");
    }

    @Override
    public AssetExpansionModel<T> assets() {
        return assets("*");
    }

    @Override
    public AssetExpansionModel<T> assets(int index) {
        return assets("" + index);
    }

    private AssetExpansionModel<T> assets(final String index) {
        return new AssetExpansionModelImpl<>(pathExpression(), "assets[" + index + "]");
    }
}
