package io.sphere.sdk.api;

import io.sphere.sdk.api.internal.ProductCommandApi;
import io.sphere.sdk.api.internal.SphereClientContainer;
import io.sphere.sdk.models.Versioned;
import io.sphere.sdk.products.Product;

public interface CommandApi extends SphereClientContainer {

    default ProductCommandApi product(final String key, final Long version){
        final Versioned<Product> versioned = Versioned.of("key=" + key, version);
        return product(versioned);
    }

    default ProductCommandApi product(final Versioned<Product> versioned){
        return ProductCommandApi.of(getSphereClient(),versioned);
    }


}
