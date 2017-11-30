package io.sphere.sdk.products.commands;

import io.sphere.sdk.api.internal.CtFuture;
import io.sphere.sdk.client.JsonEndpoint;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.commands.MetaModelByIdDeleteCommandImpl;
import io.sphere.sdk.models.Versioned;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.expansion.ProductExpansionModel;

import java.util.concurrent.Future;

import static io.sphere.sdk.client.SphereRequestUtils.urlEncode;

public class ProductDeleteApi extends MetaModelByIdDeleteCommandImpl<Product, ProductDeleteCommand, ProductExpansionModel<Product>> implements ProductDeleteCommand , CtFuture<Product> {

    private final SphereClient sphereClient;

    public static ProductDeleteApi of(final SphereClient sphereClient,final Versioned<Product> versioned) {
        return new ProductDeleteApi(sphereClient,versioned);
    }


    ProductDeleteApi(final SphereClient sphereClient, final Versioned<Product> versioned) {
        super(versioned, JsonEndpoint.of(Product.typeReference(), "/products"), ProductExpansionModel.of(), ProductDeleteCommandImpl::new);
        this.sphereClient = sphereClient;
    }


    @Override
    public Future<Product> toFuture() {
        return sphereClient.execute(this).toCompletableFuture();
    }


}
