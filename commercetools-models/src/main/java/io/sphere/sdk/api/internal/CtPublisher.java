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
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

public interface CtPublisher<T> extends Publisher<T>{


    SphereClient getSphereClient();
    SphereRequest<T> getSphereRequest();


    @Override
    default void subscribe(Subscriber<? super T> s) {

        Flowable.fromFuture(getSphereClient().execute(getSphereRequest()).toCompletableFuture()).subscribe(s);
    }

    default Flowable<T> toFlowable(){
        return Flowable.fromPublisher(this);
    }


    default CompletionStage<T> toCompletionStage(){
        return getSphereClient().execute(getSphereRequest());
    }
}
