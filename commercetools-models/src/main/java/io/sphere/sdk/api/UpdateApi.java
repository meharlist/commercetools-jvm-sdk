package io.sphere.sdk.api;

import io.sphere.sdk.api.internal.SphereClientContainer;
import io.sphere.sdk.models.Versioned;
import io.sphere.sdk.products.Product;

public interface UpdateApi extends SphereClientContainer{

    default ProductUpdateApi product(final String key, final Long version){
        final Versioned<Product> versioned = Versioned.of("key=" + key, version);//hack for simple reuse

        return product(versioned)
    }

    default ProductUpdateApi product(final Versioned<Product> versioned){
        return ProductUpdateApi.of(getSphereClient(),versioned);
    }

}
