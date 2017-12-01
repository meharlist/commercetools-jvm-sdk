package io.sphere.sdk.api;

import io.sphere.sdk.api.internal.CtPublisher;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.client.SphereRequest;
import io.sphere.sdk.commands.UpdateAction;
import io.sphere.sdk.models.LocalizedString;
import io.sphere.sdk.models.Versioned;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.PublishScope;
import io.sphere.sdk.products.commands.ProductUpdateCommand;
import io.sphere.sdk.products.commands.updateactions.ChangeName;
import io.sphere.sdk.products.commands.updateactions.Publish;
import io.sphere.sdk.products.commands.updateactions.SetKey;
import io.sphere.sdk.products.commands.updateactions.Unpublish;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductUpdateApi implements CtPublisher<Product> {


    private final SphereClient sphereClient;
    private final Versioned<Product> versioned;

    private Map<String,UpdateAction<Product>> actionsMap = new HashMap<>();



    private ProductUpdateApi(final SphereClient sphereClient, Versioned<Product> versioned){
        this.sphereClient = sphereClient;
        this.versioned = versioned;
    }

    public static ProductUpdateApi of(final SphereClient sphereClient, Versioned<Product> versiond){
        return new ProductUpdateApi(sphereClient,versiond);
    }


    ProductUpdateApi changeName(LocalizedString name){
        actionsMap.put("ChangeName",ChangeName.of(name));
        return ProductUpdateApi.this;
    }

    ProductUpdateApi publish(PublishScope scope){

        actionsMap.put("Publish", Publish.ofScope(scope));
        return ProductUpdateApi.this;
    }

    ProductUpdateApi publish(){

        actionsMap.put("Publish", Publish.ofScope(null));
        return ProductUpdateApi.this;
    }

    ProductUpdateApi unpublish(){

        actionsMap.put("Unpublish", Unpublish.of());
        return ProductUpdateApi.this;
    }


    ProductUpdateApi setKey(String key){

        actionsMap.put("SetKey", SetKey.of(key));
        return ProductUpdateApi.this;
    }



    @Override
    public Supplier<Pair<SphereClient, SphereRequest<Product>>> clientRequestSupplier() {
        return () -> Pair.of(sphereClient,ProductUpdateCommand.of(versioned, new ArrayList<>(actionsMap.values())));
    }


}
