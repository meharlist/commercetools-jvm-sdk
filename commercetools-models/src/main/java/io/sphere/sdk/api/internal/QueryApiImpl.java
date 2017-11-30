package io.sphere.sdk.api.internal;

import io.sphere.sdk.api.QueryApi;
import io.sphere.sdk.api.internal.SphereClientContainerImpl;
import io.sphere.sdk.client.SphereClient;

public class QueryApiImpl extends SphereClientContainerImpl implements QueryApi {

    public QueryApiImpl(SphereClient sphereClient) {
        super(sphereClient);
    }
}
