package io.sphere.sdk.api.internal;

import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Objects;
import java.util.concurrent.Future;
import java.util.function.Function;

public interface CtFuture<T> {


    Future<T> toFuture();

    default  <R> R toObservable(Function<? super Future<T>, ? extends R> mapper){
        Objects.requireNonNull(mapper);
        return mapper.apply(toFuture());
    }

    default Flowable<T> toFlowable(){
        return toObservable(Flowable::fromFuture);
    }
}
