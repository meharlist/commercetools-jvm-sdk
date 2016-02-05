package io.sphere.sdk.products.queries;

public interface EmbeddedProductVariantQueryModel extends CoreProductVariantQueryModel<EmbeddedProductVariantQueryModel> {

    static <T> EmbeddedProductVariantQueryModel of() {
        return new PartialProductVariantQueryModelImpl(null, null);
    }
}
