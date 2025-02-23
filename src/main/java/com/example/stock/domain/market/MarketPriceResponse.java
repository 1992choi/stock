package com.example.stock.domain.market;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class MarketPriceResponse {

    @JsonProperty("result")
    private String result;

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("server_time")
    private long serverTime;

    @JsonProperty("quote_currency")
    private String quoteCurrency;

    @JsonProperty("target_currency")
    private String targetCurrency;

    @JsonProperty("transactions")
    private List<MarketPriceResponseTransaction> transactions;

}
