package com.example.stock.domain.stock;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class StockReq {

    private String stockName;

    private LocalDate subscriptDate;

    private LocalDate listingDate;

    public Stock toEntity() {
        return Stock.builder()
                .stockName(this.stockName)
                .subscriptDate(this.subscriptDate)
                .listingDate(this.listingDate)
                .build();
    }

}
