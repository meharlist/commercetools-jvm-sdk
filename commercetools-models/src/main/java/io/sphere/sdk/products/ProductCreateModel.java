package io.sphere.sdk.products;

import com.sun.deploy.util.SessionState;
import io.sphere.sdk.api.internal.CtFuture;
import io.sphere.sdk.categories.Category;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.models.*;
import io.sphere.sdk.products.CategoryOrderHints;
import io.sphere.sdk.products.ProductCreateModel;
import io.sphere.sdk.products.ProductVariantDraft;
import io.sphere.sdk.products.commands.ProductCreateCommand;
import io.sphere.sdk.producttypes.ProductType;
import io.sphere.sdk.search.SearchKeywords;
import io.sphere.sdk.states.State;
import io.sphere.sdk.taxcategories.TaxCategory;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static io.sphere.sdk.utils.SphereInternalUtils.listOf;

public final class ProductCreateModel extends ProductDraftBuilderBase<ProductCreateModel> implements CtFuture<Product>{

    private final SphereClient  sphereClient;

    ProductCreateModel(
                        final SphereClient sphereClient,
                        final Set<ResourceIdentifier<Category>> categories,
                        @Nullable final CategoryOrderHints categoryOrderHints,
                        @Nullable final LocalizedString description,
                        @Nullable final String key,
                        @Nullable final ProductVariantDraft masterVariant,
                        @Nullable final LocalizedString metaDescription,
                        @Nullable final LocalizedString metaKeywords,
                        @Nullable final LocalizedString metaTitle,
                        final LocalizedString name,
                        final ResourceIdentifier<ProductType> productType,
                        @Nullable final Boolean publish,
                        final SearchKeywords searchKeywords,
                        final LocalizedString slug,
                        @Nullable final Reference<State> state,
                        @Nullable final Reference<TaxCategory> taxCategory,
                        final List<ProductVariantDraft> variants) {
        super(categories, categoryOrderHints, description, key, masterVariant, metaDescription, metaKeywords, metaTitle,
                name, productType, publish, searchKeywords, slug, state, taxCategory, variants);
        init();
        this.sphereClient = sphereClient;
    }

    private void init(){
        variants = Optional.ofNullable(variants).orElse(Collections.emptyList());
        categories = Optional.ofNullable(categories).orElse(Collections.emptySet());
        searchKeywords = Optional.ofNullable(searchKeywords).orElse(SearchKeywords.of());
    }

    public static ProductCreateModel of(final SphereClient sphereClient,
                                         final ResourceIdentifiable<ProductType> productType,
                                         final LocalizedString name,
                                         final LocalizedString slug,
                                         final List<ProductVariantDraft> allVariants) {
        final ProductVariantDraft masterVariant = allVariants.stream().findFirst().orElse(null);
        final List<ProductVariantDraft> variants = allVariants.stream().skip(1).collect(Collectors.toList());

        return of(sphereClient,productType, name, slug, masterVariant)
                .plusVariants(variants);
    }

    public static ProductCreateModel of(final SphereClient sphereClient,
                                         final ResourceIdentifiable<ProductType> productType,
                                         final LocalizedString name,
                                         final LocalizedString slug,
                                         @Nullable final ProductVariantDraft masterVariant) {
        return of(sphereClient,productType.toResourceIdentifier(),name,slug,masterVariant);
    }


    public ProductCreateModel variants(final List<ProductVariantDraft> variants) {
        this.variants = variants != null
                ? Collections.unmodifiableList(new ArrayList<>(variants))
                : Collections.emptyList();
        return ProductCreateModel.this;
    }

    public ProductCreateModel plusVariants(final ProductVariantDraft variantToAdd) {
        return variants(listOf(this.variants, variantToAdd));
    }

    public ProductCreateModel plusVariants(final List<ProductVariantDraft> variantsToAdd) {
        return variants(listOf(this.variants, variantsToAdd));
    }


    public ProductCreateModel categories(final List<Reference<Category>> categories) {
        return categories(new LinkedHashSet<>(categories));
    }

    /**
     * Adds categories to this product draft. Alias for {@link #categories(List)} which takes the category objects as parameter.
     *
     * @param categories categories which the product belongs to
     * @return this builder
     */
    public ProductCreateModel categoriesAsObjectList(final List<Category> categories) {
        final List<Reference<Category>> referenceList = categories.stream()
                .filter(Objects::nonNull)
                .map(Category::toReference)
                .collect(Collectors.toList());
        return categories(referenceList);
    }


    public ProductCreateModel state(@Nullable final Referenceable<State> state) {
        this.state = Optional.ofNullable(state).map(Referenceable::toReference).orElse(null);
        return ProductCreateModel.this;
    }

    SphereClient getSphereClient() {
        return sphereClient;
    }

    @Override
    public Future<Product> toFuture() {
        ProductDraft draft = build();
        return getSphereClient().execute(ProductCreateCommand.of(draft)).toCompletableFuture();
    }
}
