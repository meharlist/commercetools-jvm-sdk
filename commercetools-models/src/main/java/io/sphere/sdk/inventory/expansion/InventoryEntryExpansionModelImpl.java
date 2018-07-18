package io.sphere.sdk.inventory.expansion;

import io.sphere.sdk.channels.expansion.ChannelExpansionModel;
import io.sphere.sdk.expansion.ExpansionModelImpl;
import io.sphere.sdk.products.expansion.CustomExpansionModel;
import io.sphere.sdk.products.expansion.CustomExpansionModelImpl;

final class InventoryEntryExpansionModelImpl<T> extends ExpansionModelImpl<T> implements InventoryEntryExpansionModel<T> {

    InventoryEntryExpansionModelImpl() {
    }

    @Override
    public ChannelExpansionModel<T> supplyChannel() {
        return ChannelExpansionModel.of(buildPathExpression(), "supplyChannel");
    }

    @Override
    public CustomExpansionModel<T> custom() {
        return new CustomExpansionModelImpl<>(pathExpression(), "custom");
    }
}
