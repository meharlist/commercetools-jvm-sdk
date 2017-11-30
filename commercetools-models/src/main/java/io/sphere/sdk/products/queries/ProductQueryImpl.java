package io.sphere.sdk.products.queries;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.http.NameValuePair;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.expansion.ProductExpansionModel;
import io.sphere.sdk.products.search.PriceSelection;
import io.sphere.sdk.queries.MetaModelQueryDslBuilder;
import io.sphere.sdk.queries.MetaModelQueryDslImpl;
import io.sphere.sdk.queries.PagedQueryResult;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.Future;

import static io.sphere.sdk.products.search.PriceSelectionQueryParameters.extractPriceSelectionFromHttpQueryParameters;
import static io.sphere.sdk.products.search.PriceSelectionQueryParameters.getQueryParametersWithPriceSelection;

/**
 {@doc.gen summary products}
 */
final class ProductQueryImpl extends MetaModelQueryDslImpl<Product, ProductQueryApi, ProductQueryModel, ProductExpansionModel<Product>> implements ProductQueryApi {

    SphereClient sphereClient;

    ProductQueryImpl(final SphereClient sphereClient){
        super(ProductEndpoint.ENDPOINT.endpoint(), ProductQueryApi.resultTypeReference(), ProductQueryModel.of(), ProductExpansionModel.of(), t -> new ProductQueryImpl(sphereClient));
        this.sphereClient = sphereClient;
    }

    private ProductQueryImpl(final MetaModelQueryDslBuilder<Product, ProductQueryApi, ProductQueryModel, ProductExpansionModel<Product>> builder) {
        super(builder);
    }



    @Override
    public ProductQueryApi withPriceSelection(@Nullable final PriceSelection priceSelection) {
        final List<NameValuePair> resultingParameters = getQueryParametersWithPriceSelection(priceSelection, additionalHttpQueryParameters());
        return withAdditionalHttpQueryParameters(resultingParameters);
    }

    @Nullable
    @Override
    public PriceSelection getPriceSelection() {
        return extractPriceSelectionFromHttpQueryParameters(additionalHttpQueryParameters());
    }

    @Override
    public Future<PagedQueryResult<Product>> toFuture() {
        return sphereClient.execute(this).toCompletableFuture();
    }
}