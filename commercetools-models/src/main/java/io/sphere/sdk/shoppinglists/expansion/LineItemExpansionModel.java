package io.sphere.sdk.shoppinglists.expansion;

import io.sphere.sdk.expansion.ExpansionPathContainer;
import io.sphere.sdk.products.expansion.CustomExpansionModel;
import io.sphere.sdk.producttypes.expansion.ProductTypeExpansionModel;

import java.util.List;

public interface LineItemExpansionModel<T> extends ExpansionPathContainer<T> {

    ExpansionPathContainer<T> variant();

    ProductTypeExpansionModel<T> productType();

    ExpansionPathContainer<T> productSlug();

    CustomExpansionModel<T> custom();

    static <T> LineItemExpansionModel<T> of(final List<String> parentPath, final String path){
        return new LineItemExpansionModelImpl<>(parentPath, path);
    }
}
