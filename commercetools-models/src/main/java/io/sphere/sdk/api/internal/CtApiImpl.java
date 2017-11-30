package io.sphere.sdk.api.internal;

import io.sphere.sdk.api.*;
import io.sphere.sdk.client.SphereClient;

import java.util.Objects;

public class CtApiImpl implements CtApi {

    private final SphereClient sphereClient;


    public CtApiImpl(final SphereClient sphereClient) {
        Objects.requireNonNull(sphereClient);
        this.sphereClient = sphereClient;
    }

    @Override
    public SphereClient getSphereClient() {
        return sphereClient;
    }

}
