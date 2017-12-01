package io.sphere.sdk.api.internal;

import io.sphere.sdk.api.CommandApi;
import io.sphere.sdk.client.SphereClient;

public class CommandApiImpl extends SphereClientContainerImpl implements CommandApi{


    public CommandApiImpl(SphereClient sphereClient) {
        super(sphereClient);
    }


}
