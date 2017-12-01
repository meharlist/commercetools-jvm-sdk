package io.sphere.sdk.api;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableContainer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customergroups.CustomerGroupFixtures;
import io.sphere.sdk.models.LocalizedString;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.producttypes.ProductType;
import io.sphere.sdk.queries.PagedQueryResult;
import io.sphere.sdk.suppliers.VariantsCottonTShirtProductDraftSupplier;
import io.sphere.sdk.test.IntegrationTest;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.function.BiConsumer;

import static io.sphere.sdk.products.ProductFixtures.withProductType;
import static io.sphere.sdk.test.SphereTestUtils.randomKey;
import static io.sphere.sdk.test.SphereTestUtils.randomSlug;


public class ApiIntegrationTest extends IntegrationTest {

    public static CtApi api = CtApi.of(client());

    public static Flowable<Product> products = Flowable.fromPublisher(api.query().product()).flatMapIterable(PagedQueryResult::getResults);

    public static File image = new File(ClassLoader.getSystemResource("ct_logo_farbe.gif").getFile());

    @Before
    public void clean(){
        products.parallel(20)
                .runOn(Schedulers.io())
                .flatMap(product -> api.update().product(product).unpublish())
                .flatMap(product -> api.delete().product(product))
                .sequential()
                .retry(2)
                .subscribe(product -> {},Throwable::printStackTrace);
    }



    @Test
    public void testCreate() throws Exception {


        testFixture(client(),((productType, draftSupplier) -> {

                    Flowable.range(0,10)
                            .flatMap(i ->
                                    api.create().product(productType.toResourceIdentifier(), LocalizedString.ofEnglish("Shirts_"+randomKey()), randomSlug(), draftSupplier.get().getVariants())
                            )
                            .blockingSubscribe(product -> System.out.println("Created product "+product.getMasterData().getStaged().getName()),Throwable::printStackTrace);


                    TestSubscriber<Product> testSubscriber = new TestSubscriber<>();
                    testSubscriber.assertNotSubscribed();
                    products.subscribe(testSubscriber);

                    testSubscriber.assertSubscribed();
                    testSubscriber.assertValueCount(10);
                }));


    }

    @Test
    public void testCommand() throws Exception {


        testFixture(client(),((productType, draftSupplier) -> {
                    creat10Products(productType,draftSupplier);

                    //Execute Upload command
                            Flowable.fromPublisher(api.query().product())
                                    .flatMapIterable(PagedQueryResult::getResults)
                                    .flatMap( product -> api.command().product(product).uploadImage(image))
                                    .blockingSubscribe(product -> System.out.println());


                    TestSubscriber<Product> testSubscriber = new TestSubscriber<>();
                    testSubscriber.assertNotSubscribed();

                    products.subscribe(testSubscriber);

                    testSubscriber.assertSubscribed();
                    testSubscriber.assertValueCount(10);
                    testSubscriber.assertValueAt(0,product -> !product.getMasterData().getStaged().getMasterVariant().getImages().isEmpty());

                }));


    }


    @Test
    public void testDelete() throws Exception {



        testFixture(client(),((productType, draftSupplier) -> {
            creat10Products(productType,draftSupplier);

            products.flatMap(product -> api.delete().product(product))
                    .blockingSubscribe(product -> {}, Throwable::printStackTrace);

            TestSubscriber<Product> testSubscriber = new TestSubscriber<>();
            testSubscriber.assertNotSubscribed();
            products.subscribe(testSubscriber);
            testSubscriber.assertSubscribed();
            testSubscriber.assertValueCount(0);
        }));



    }

    @Test
    public void testUpdate(){
        testFixture(client(),((productType, draftSupplier) -> {

            creat10Products(productType,draftSupplier);
             products
                    .flatMap(
                    product ->   api.update()
                                    .product(product)
                                    .changeName(LocalizedString.ofEnglish("NewName"))
                                    .setKey(randomKey())
                                    .publish()

            ).subscribe();

            TestSubscriber<Product> testSubscriber = new TestSubscriber<>();
            testSubscriber.assertNotSubscribed();

            products.subscribe(testSubscriber);

            testSubscriber.assertSubscribed();
            testSubscriber.assertValueCount(10);
            testSubscriber.assertValueAt(0,
                    product ->LocalizedString.ofEnglish("NewName").equals(product.getMasterData().getStaged().getName()));


        }));
    }



    public void testFixture(SphereClient sphereClient, BiConsumer<ProductType, VariantsCottonTShirtProductDraftSupplier> biconsumer) {

        CustomerGroupFixtures.withB2cCustomerGroup(client(), customerGroup ->
                withProductType(client(), "Shirts", productType -> {
                    VariantsCottonTShirtProductDraftSupplier draftSupplier = new VariantsCottonTShirtProductDraftSupplier(productType, "TShirt", customerGroup);
                    biconsumer.accept(productType, draftSupplier);
                }));

    }

    public static void creat10Products(ProductType productType,VariantsCottonTShirtProductDraftSupplier draftSupplier){
        Flowable.range(0,10)
                .flatMap(i ->
                        api.create().product(productType.toResourceIdentifier(), LocalizedString.ofEnglish("Shirts_"+randomKey()), randomSlug(), draftSupplier.get().getVariants())
                ).subscribe(product -> {},Throwable::printStackTrace);
    }

}
