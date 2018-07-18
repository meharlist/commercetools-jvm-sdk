package io.sphere.sdk.carts.expansion;

import io.sphere.sdk.products.expansion.CustomExpansionModel;

public interface CustomLineItemExpansionModel<T> {
    DiscountedLineItemPricePerQuantityExpansionModel<T> discountedPricePerQuantity();

    DiscountedLineItemPricePerQuantityExpansionModel<T> discountedPricePerQuantity(int index);

    CustomExpansionModel<T> custom();
}
