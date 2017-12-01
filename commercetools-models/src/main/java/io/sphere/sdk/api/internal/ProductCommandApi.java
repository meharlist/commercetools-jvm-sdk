package io.sphere.sdk.api.internal;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.models.Versioned;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.commands.ProductImageUploadCommand;

import java.io.File;

public class ProductCommandApi {

    private final SphereClient sphereClient;
    private final Versioned<Product> versioned;

    private ProductCommandApi(final SphereClient sphereClient, Versioned<Product> versioned){
        this.sphereClient = sphereClient;
        this.versioned = versioned;
    }

    public static ProductCommandApi of(final SphereClient sphereClient, Versioned<Product> versiond){
        return new ProductCommandApi(sphereClient,versiond);
    }

    public ProductImageUploadCommand uploadImage(final File body){
        return ProductImageUploadCommand.ofMasterVariant(body,versioned.getId(),getSphereClient());
    }

    private SphereClient getSphereClient() {
        return sphereClient;
    }
}
