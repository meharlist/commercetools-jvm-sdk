package io.sphere.sdk.payments.expansion;

import io.sphere.sdk.customers.expansion.CustomerExpansionModel;
import io.sphere.sdk.expansion.ExpansionModelImpl;
import io.sphere.sdk.products.expansion.CustomExpansionModel;
import io.sphere.sdk.products.expansion.CustomExpansionModelImpl;

import java.util.List;

final class PaymentExpansionModelImpl<T> extends ExpansionModelImpl<T> implements PaymentExpansionModel<T> {
    PaymentExpansionModelImpl() {
        super();
    }

    public PaymentExpansionModelImpl(final List<String> parentPath, final String path) {
        super(parentPath, path);
    }

    @Override
    public CustomerExpansionModel<T> customer() {
        return CustomerExpansionModel.of(buildPathExpression(), "customer");
    }

    @Override
    public PaymentStatusExpansionModel<T> paymentStatus() {
        return new PaymentStatusExpansionModelImpl<>(buildPathExpression(), "paymentStatus");
    }

    @Override
    public CustomExpansionModel<T> custom() {
        return new CustomExpansionModelImpl<>(pathExpression(), "custom");
    }

    @Override
    public CustomExpansionModel<T> interfaceInteractions() {
        return interfaceInteractions("*");
    }

    @Override
    public CustomExpansionModel<T> interfaceInteractions(final int index) {
        return interfaceInteractions("" + index);
    }

    private CustomExpansionModel<T> interfaceInteractions(final String index) {
        return new CustomExpansionModelImpl<>(pathExpression(), "interfaceInteractions[" + index + "]");
    }
}
