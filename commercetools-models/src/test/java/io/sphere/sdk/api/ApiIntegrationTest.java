package io.sphere.sdk.api;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.subscribers.TestSubscriber;
import io.sphere.sdk.products.Image;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.ProductVariantDraftBuilder;
import io.sphere.sdk.queries.PagedQueryResult;
import io.sphere.sdk.test.IntegrationTest;
import org.junit.Test;

import java.util.ArrayList;


public class ApiIntegrationTest extends IntegrationTest {

    @Test
    public void test() throws Exception {

        CtApi api = CtApi.of(client());

//        api.create()
//                .product(null, null, null, new ArrayList<>())
//                .plusVariants(ProductVariantDraftBuilder.of().images(Image.of()))
//                .toObservable(Single::fromFuture)
//                .subscribe(product -> System.out.println(product), error -> System.out.println(error));
//
//        api.delete()
//                .product(null)
//                .toObservable(Single::fromFuture)
//                .subscribe(product -> System.out.println(product), error -> System.out.println(error));

        Flowable<Product> products =  api.query().product().withOffset(500).toFlowable()
                .takeWhile(productPagedQueryResult -> productPagedQueryResult.getCount() > 0)
                .flatMapIterable(PagedQueryResult::getResults);


        api.query().product().withOffset(500).toObservable(Single::fromFuture).blockingGet()
        TestSubscriber<Product> testSubscriber = new TestSubscriber<>();
        testSubscriber.assertNotSubscribed();
        products.subscribe(testSubscriber);
        testSubscriber.assertSubscribed();
        testSubscriber.assertValueCount(4);


//        do add search and update Action


    }
}
