package io.sphere.sdk.api.internal;

import io.sphere.sdk.api.SearchApi;
import io.sphere.sdk.client.SphereClient;

public class SearchApiImpl extends SphereClientContainerImpl implements SearchApi {
    public SearchApiImpl(SphereClient sphereClient) {
        super(sphereClient);
    }
}
