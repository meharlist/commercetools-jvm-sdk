package io.sphere.sdk.api.internal;

import io.sphere.sdk.api.UpdateApi;
import io.sphere.sdk.client.SphereClient;

public class UpdateApiImpl extends SphereClientContainerImpl implements UpdateApi {
    public UpdateApiImpl(SphereClient sphereClient) {
        super(sphereClient);
    }
}
