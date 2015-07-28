package io.sphere.sdk.customers.commands;

import io.sphere.sdk.commands.ByIdDeleteCommandImpl;
import io.sphere.sdk.commands.DeleteCommand;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.models.Versioned;

/**
 * Deletes a customer.
 *
 * {@include.example io.sphere.sdk.customers.commands.CustomerDeleteCommandTest#execution()}
 */
public class CustomerDeleteCommandImpl extends ByIdDeleteCommandImpl<Customer> {
    private CustomerDeleteCommandImpl(final Versioned<Customer> versioned) {
        super(versioned, CustomerEndpoint.ENDPOINT);
    }

    public static DeleteCommand<Customer> of(final Versioned<Customer> versioned) {
        return new CustomerDeleteCommandImpl(versioned);
    }
}
