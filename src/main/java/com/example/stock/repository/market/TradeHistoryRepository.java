package com.example.stock.repository.market;

import com.example.stock.domain.market.TradeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory, Long> {

    long countByMarketCodeAndTradeDate(String marketCode, LocalDate tradeDate);

    TradeHistory findTopByMarketCodeAndTradeDateOrderByCreatedAtAsc(String marketCode, LocalDate tradeDate);

}
