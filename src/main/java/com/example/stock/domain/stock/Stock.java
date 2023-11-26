package com.example.stock.domain.stock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity(name = "TB_STOCK")
public class Stock {

    @Id
    @Column(name = "STOCK_ID")
    private Long stockId;

    @Column(name = "STOCK_NAME")
    private String stockName;

    @Column(name = "SUBSCRIPT_DATE")
    private LocalDate subscriptDate;

    @Column(name = "LISTING_DATE")
    private LocalDate listingDate;

}
