package io.sphere.sdk.retry;

import io.sphere.sdk.models.Base;

import javax.annotation.Nullable;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

final class AsyncRetrySupervisorImpl extends Base implements AsyncRetrySupervisor {
    private final AutoCloseable service;
    private final List<RetryRule> retryRules;
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(0);

    AsyncRetrySupervisorImpl(final AutoCloseable service, final List<RetryRule> retryRules) {
        this.service = service;
        this.retryRules = retryRules;
    }

    @Override
    public <P, R> CompletionStage<R> supervise(final Function<P, CompletionStage<R>> f, @Nullable final P parameterObject) {
        final CompletionStage<R> initialCompletionStage = f.apply(parameterObject);
        final CompletableFuture<R> result = new CompletableFuture<>();
        initialCompletionStage.whenComplete((res, firstError) -> {
            final boolean isErrorCase = firstError != null;
            if (isErrorCase) {
                final RetryOperationContext<P, R> retryOperationContext = createFirstRetryOperationContext(firstError, result, f, parameterObject);
                handle(retryOperationContext);
            } else {
                result.complete(res);
            }
        });
        return result;
    }

    @Override
    public void close() throws Exception {
        executor.shutdownNow();
    }

    private <P, R> RetryOperationContext<P, R> createFirstRetryOperationContext(final Throwable throwable, final CompletableFuture<R> result, final Function<P, CompletionStage<R>> f, final P parameterObject) {
        final long attemptCount = 1L;
        final Instant now = Instant.now();
        final AttemptErrorResult<P> firstAttemptErrorResult = new AttemptErrorResultImpl<>(throwable, now, parameterObject);
        final AttemptErrorResult<P> latestErrorResult = firstAttemptErrorResult;
        return new RetryOperationContextImpl<>(attemptCount, firstAttemptErrorResult, latestErrorResult, result, f, service, this::schedule);
    }

    private void schedule(final Runnable r, final Duration d) {
        executor.schedule(r, d.toMillis(), TimeUnit.MILLISECONDS);
    }

    private <P, R> void handle(final RetryOperationContext<P, R> retryOperationContext) {
        final RetryOperation retryOperation = getRetryOperation(retryOperationContext);
        @Nullable final RetryOutput<P, R> output = retryOperation.handle(retryOperationContext);
        if (output != null) {
            output.getStage().whenComplete((res, error) -> {
                final boolean isErrorCase = error != null;
                if (isErrorCase) {
                    final AttemptErrorResult<P> attemptErrorResult = new AttemptErrorResultImpl<>(error, Instant.now(), output.getParameterObject());
                    final RetryOperationContext<P, R> nextContext = getNextContext(attemptErrorResult, retryOperationContext);
                    handle(nextContext);
                } else {
                    retryOperationContext.getResult().complete(res);
                }
            });
        }
    }

    private <P> RetryOperation getRetryOperation(final RetryContext<P> retryContext) {
        return retryRules.stream()
                .filter(rule -> rule.test(retryContext))
                .findFirst()
                .map(rule -> rule.selectRetryOperation(retryContext))
                .orElseGet(() -> RetryOperations.giveUpAndSendLatestException());
    }

    private <P, R> RetryOperationContext<P, R> getNextContext(final AttemptErrorResult<P> attemptErrorResult, final RetryOperationContext<P, R> parentContext) {
        final long attemptCount = parentContext.getAttemptCount() + 1;
        final AttemptErrorResult<P> firstAttemptErrorResult = parentContext.getFirst();
        return new RetryOperationContextImpl<>(attemptCount, firstAttemptErrorResult, attemptErrorResult, parentContext.getResult(), parentContext.getFunction(), parentContext.getService(), this::schedule);
    }
}
