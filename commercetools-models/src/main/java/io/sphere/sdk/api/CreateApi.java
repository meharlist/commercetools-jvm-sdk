package io.sphere.sdk.api;

import io.sphere.sdk.api.internal.SphereClientContainer;
import io.sphere.sdk.models.LocalizedString;
import io.sphere.sdk.models.ResourceIdentifiable;
import io.sphere.sdk.models.ResourceIdentifier;
import io.sphere.sdk.products.ProductCreateModel;
import io.sphere.sdk.products.ProductVariantDraft;
import io.sphere.sdk.producttypes.ProductType;

import javax.annotation.Nullable;
import java.util.List;

public interface CreateApi extends SphereClientContainer {

    default ProductCreateModel product(final ResourceIdentifier<ProductType> productType, final LocalizedString name, final LocalizedString slug, final List<ProductVariantDraft> allVariants){
        return ProductCreateModel.of(getSphereClient(),  productType,  name,  slug, allVariants);
    }

    default ProductCreateModel product(final ResourceIdentifier<ProductType> productType, final LocalizedString name, final LocalizedString slug, @Nullable final ProductVariantDraft masterVariant) {
        return ProductCreateModel.of(getSphereClient(),  productType,  name,  slug, masterVariant);
    }
}
