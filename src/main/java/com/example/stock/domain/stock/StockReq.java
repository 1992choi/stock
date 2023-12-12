package com.example.stock.domain.stock;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class StockReq {

    @Schema(description = "공모주 이름", example = "삼성전자")
    private String stockName;

    @Schema(description = "청약일", example = "2023-12-01")
    private LocalDate subscriptDate;

    @Schema(description = "상장일", example = "2023-12-10")
    private LocalDate listingDate;

    public Stock toEntity() {
        return Stock.builder()
                .stockName(this.stockName)
                .subscriptDate(this.subscriptDate)
                .listingDate(this.listingDate)
                .build();
    }

}
