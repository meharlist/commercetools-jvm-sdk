package io.sphere.sdk.api;

import io.sphere.sdk.api.internal.SphereClientContainer;
import io.sphere.sdk.products.queries.ProductQueryApi;

public interface QueryApi extends SphereClientContainer {

    default ProductQueryApi product(){
        return ProductQueryApi.of(getSphereClient());
    }

}
