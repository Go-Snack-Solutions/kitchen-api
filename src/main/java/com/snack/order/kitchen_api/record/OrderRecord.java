package com.snack.order.kitchen_api.record;

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



