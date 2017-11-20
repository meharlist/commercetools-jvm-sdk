package io.sphere.sdk.api;

import io.sphere.sdk.client.SphereClient;

public interface FlowApi {

    FlowApi of(SphereClient sphereClient);

    CreateApi create();

    DeleteApi delete();

    QueryApi query();

    SearchApi search();

}
