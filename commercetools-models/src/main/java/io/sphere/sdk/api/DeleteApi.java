package io.sphere.sdk.api;

import io.sphere.sdk.api.internal.SphereClientContainer;
import io.sphere.sdk.models.Versioned;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.commands.ProductDeleteApi;

public interface DeleteApi extends SphereClientContainer {

    default ProductDeleteApi product(Versioned<Product> versioned) {
        return ProductDeleteApi.of(getSphereClient(),versioned);
    }

    default ProductDeleteApi product(String key, Long version) {
        final Versioned<Product> versioned = Versioned.of("key=" + key, version);
        return product(versioned);
    }

}
