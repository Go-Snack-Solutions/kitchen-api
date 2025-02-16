package com.snack.order.kitchenapi.record;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record OrderRecord(
        @JsonProperty("orderId") String orderId,
        @JsonProperty("items") List<ItemRecord> items,
        @JsonProperty("orderStatus") String orderStatus
) {
    @JsonCreator
    public OrderRecord {
        // Jackson consegue criar a instância corretamente
    }
}



