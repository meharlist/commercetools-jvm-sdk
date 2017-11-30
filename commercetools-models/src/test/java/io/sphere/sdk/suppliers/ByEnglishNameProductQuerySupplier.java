package io.sphere.sdk.suppliers;

import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.queries.ProductQueryApi;
import io.sphere.sdk.products.queries.ProductQueryModel;
import io.sphere.sdk.queries.QueryPredicate;
import io.sphere.sdk.queries.Query;

import java.util.Locale;
import java.util.function.Supplier;

public class ByEnglishNameProductQuerySupplier implements Supplier<Query<Product>> {
    @Override
    public Query<Product> get() {
        final QueryPredicate<Product> predicate = ProductQueryModel.of().
                masterData().current().name().lang(Locale.ENGLISH).is("simple cotton t-shirt");
        return ProductQueryApi.of().withPredicates(predicate);
    }
}
