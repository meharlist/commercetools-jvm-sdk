package io.sphere.sdk.api.internal;

import com.sun.tools.javac.comp.Flow;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.client.SphereRequest;
import io.sphere.sdk.products.commands.updateactions.Publish;
import jdk.management.resource.internal.FutureWrapper;
import org.apache.commons.lang3.tuple.Pair;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.function.Supplier;

public interface CtPublisher<T> extends Publisher<T>{


    Supplier<Pair<SphereClient,SphereRequest<T>>> clientRequestSupplier();

    @Override
    default void subscribe(Subscriber<? super T> s) {
        SphereClient sphereClient = clientRequestSupplier().get().getLeft();
        SphereRequest<T> sphereRequest = clientRequestSupplier().get().getRight();
        Flowable.fromFuture(sphereClient.execute(sphereRequest).toCompletableFuture()).subscribe(s);
    }

    default Flowable<T> toFlowable(){
        return Flowable.fromPublisher(this);
    }


}
