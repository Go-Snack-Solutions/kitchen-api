package com.snack.order.kitchen_api.record;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemRecord(
        @JsonProperty("itemId") String itemId,
        @JsonProperty("itemName") String itemName,
        @JsonProperty("itemPrice") Double itemPrice
) {
    @JsonCreator
    public ItemRecord {
    }
}