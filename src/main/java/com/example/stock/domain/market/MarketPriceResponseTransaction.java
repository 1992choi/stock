package com.example.stock.domain.market;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class MarketPriceResponseTransaction {

    private String id;

    private long timestamp;

    private String price;

    private String qty;

    @JsonProperty("is_seller_maker")
    private boolean isSellerMaker;

}
