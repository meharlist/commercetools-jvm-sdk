package io.sphere.sdk.inventory.expansion;

import io.sphere.sdk.channels.expansion.ChannelExpansionModel;
import io.sphere.sdk.inventory.InventoryEntry;
import io.sphere.sdk.products.expansion.CustomExpansionModel;

public interface InventoryEntryExpansionModel<T> {
    ChannelExpansionModel<T> supplyChannel();

    CustomExpansionModel<T> custom();

    static InventoryEntryExpansionModel<InventoryEntry> of() {
        return new InventoryEntryExpansionModelImpl<>();
    }
}
