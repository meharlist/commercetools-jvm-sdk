package io.sphere.sdk.productdiscounts.commands.updateactions;

import io.sphere.sdk.commands.UpdateAction;
import io.sphere.sdk.productdiscounts.ProductDiscount;
import io.sphere.sdk.productdiscounts.ProductDiscountValue;

/**
 * {@include.example io.sphere.sdk.productdiscounts.commands.ProductDiscountUpdateCommandTest#changeValue()}
 */
public class ChangeValue extends UpdateAction<ProductDiscount> {
    private final ProductDiscountValue value;

    private ChangeValue(final ProductDiscountValue value) {
        super("changeValue");
        this.value = value;
    }

    public static ChangeValue of(final ProductDiscountValue value) {
        return new ChangeValue(value);
    }

    public ProductDiscountValue getValue() {
        return value;
    }
}