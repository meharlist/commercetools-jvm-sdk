package io.sphere.sdk.api.internal;

import io.sphere.sdk.client.SphereClient;

import java.util.Objects;

public class SphereClientContainerImpl implements SphereClientContainer {

    private final SphereClient sphereClient;


    public SphereClientContainerImpl(final SphereClient sphereClient) {
        Objects.requireNonNull(sphereClient);
        this.sphereClient = sphereClient;
    }

    @Override
    public SphereClient getSphereClient() {
        return sphereClient;
    }

}
