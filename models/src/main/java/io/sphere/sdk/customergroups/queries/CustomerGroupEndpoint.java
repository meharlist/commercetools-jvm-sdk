package io.sphere.sdk.customergroups.queries;

import io.sphere.sdk.customergroups.CustomerGroup;
import io.sphere.sdk.http.JsonEndpoint;

final class CustomerGroupEndpoint {
    public static final JsonEndpoint<CustomerGroup> ENDPOINT = JsonEndpoint.of(CustomerGroup.typeReference(), "/customer-groups");
}
