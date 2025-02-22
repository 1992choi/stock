package com.example.stock.domain.market;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@ToString
@Entity(name = "TB_MARKET_PRICE")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class MarketPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MARKET_PRICE_ID")
    private Long marketPriceId;

    @Column(name = "MARKET_CODE")
    private String marketCode;

    @Column(name = "MARKET_PRICE")
    private BigDecimal marketPrice;

    @CreatedDate
    @Column(updatable = false) // 생성 후 변경 불가
    private LocalDateTime createdAt;

    @Builder
    public MarketPrice(Long marketPriceId, String marketCode, BigDecimal marketPrice) {
        this.marketPriceId = marketPriceId;
        this.marketCode = marketCode;
        this.marketPrice = marketPrice;
    }

}
