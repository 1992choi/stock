package com.example.stock.domain.stock;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StockRes {

    private Long stockId;

    private String stockName;

    private LocalDate subscriptDate;

    private LocalDate listingDate;

    public StockRes(Stock stock) {
        this.stockId = stock.getStockId();
        this.stockName = stock.getStockName();
        this.subscriptDate = stock.getSubscriptDate();
        this.listingDate = stock.getListingDate();
    }

}
