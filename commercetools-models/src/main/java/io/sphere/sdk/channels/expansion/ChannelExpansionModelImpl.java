package io.sphere.sdk.channels.expansion;

import io.sphere.sdk.expansion.ExpansionModelImpl;
import io.sphere.sdk.products.expansion.CustomExpansionModel;
import io.sphere.sdk.products.expansion.CustomExpansionModelImpl;

import java.util.List;

final class ChannelExpansionModelImpl<T> extends ExpansionModelImpl<T> implements ChannelExpansionModel<T> {
    public ChannelExpansionModelImpl(final List<String> parentPath, final String path) {
        super(parentPath, path);
    }

    ChannelExpansionModelImpl() {
        super();
    }

    @Override
    public CustomExpansionModel<T> custom() {
        return new CustomExpansionModelImpl<>(pathExpression(), "custom");
    }
}
