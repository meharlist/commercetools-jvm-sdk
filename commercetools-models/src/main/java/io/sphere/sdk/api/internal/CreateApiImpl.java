package io.sphere.sdk.api.internal;

import io.sphere.sdk.api.CreateApi;
import io.sphere.sdk.client.SphereClient;

public class CreateApiImpl extends SphereClientContainerImpl implements CreateApi {

    public CreateApiImpl(SphereClient sphereClient) {
        super(sphereClient);
    }
}
