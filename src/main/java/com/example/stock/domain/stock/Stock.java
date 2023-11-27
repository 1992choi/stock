package com.example.stock.domain.stock;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@Entity(name = "TB_STOCK")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STOCK_ID")
    private Long stockId;

    @Column(name = "STOCK_NAME")
    private String stockName;

    @Column(name = "SUBSCRIPT_DATE")
    private LocalDate subscriptDate;

    @Column(name = "LISTING_DATE")
    private LocalDate listingDate;

}
