package io.sphere.sdk.api.internal;

import io.sphere.sdk.api.DeleteApi;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.models.Versioned;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.commands.ProductDeleteApi;

import java.util.concurrent.Future;

public class DeleteApiImpl extends SphereClientContainerImpl implements DeleteApi{


    public DeleteApiImpl(SphereClient sphereClient) {
        super(sphereClient);
    }


}
