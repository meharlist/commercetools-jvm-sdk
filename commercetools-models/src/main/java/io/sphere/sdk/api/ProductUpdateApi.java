package io.sphere.sdk.api;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.models.Versioned;
import io.sphere.sdk.products.Product;

public class ProductUpdateApi {


    private final SphereClient sphereClient;
    private final Versioned<Product> versioned;


    private ProductUpdateApi(final SphereClient sphereClient, Versioned<Product> versioned){
        this.sphereClient = sphereClient;
        this.versioned = versioned;
    }

    public static ProductUpdateApi of(final SphereClient sphereClient, Versioned<Product> versiond){
        return new ProductUpdateApi(sphereClient,versiond);
    }




















}
