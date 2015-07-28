package io.sphere.sdk.carts.commands;

import io.sphere.sdk.carts.Cart;
import io.sphere.sdk.commands.ByIdDeleteCommandImpl;
import io.sphere.sdk.commands.DeleteCommand;
import io.sphere.sdk.models.Versioned;

/**
 * Deletes a cart in SPHERE.IO.
 *
 */
public final class CartDeleteCommandImpl extends ByIdDeleteCommandImpl<Cart> {

    private CartDeleteCommandImpl(final Versioned<Cart> versioned) {
        super(versioned, CartEndpoint.ENDPOINT);
    }

    public static DeleteCommand<Cart> of(final Versioned<Cart> versioned) {
        return new CartDeleteCommandImpl(versioned);
    }
}
