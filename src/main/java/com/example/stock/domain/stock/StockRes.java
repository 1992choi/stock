package com.example.stock.domain.stock;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StockRes {

    @Schema(description = "공모주 ID", example = "1")
    private Long stockId;

    @Schema(description = "공모주 이름", example = "삼성전자")
    private String stockName;

    @Schema(description = "청약일", example = "2023-12-01")
    private LocalDate subscriptDate;

    @Schema(description = "상장일", example = "2023-12-10")
    private LocalDate listingDate;

    public StockRes(Stock stock) {
        this.stockId = stock.getStockId();
        this.stockName = stock.getStockName();
        this.subscriptDate = stock.getSubscriptDate();
        this.listingDate = stock.getListingDate();
    }

}
