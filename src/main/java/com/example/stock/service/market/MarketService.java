package com.example.stock.service.market;

import com.example.stock.domain.market.MarketPrice;
import com.example.stock.domain.market.MarketPriceResponse;
import com.example.stock.domain.market.TradeHistory;
import com.example.stock.repository.market.MarketPriceRepository;
import com.example.stock.repository.market.TradeHistoryRepository;
import com.example.stock.service.noti.TelegramService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketService {

    private final MarketPriceRepository marketPriceRepository;
    private final TradeHistoryRepository tradeHistoryRepository;
    private final TelegramService telegramService;

    public void executeTrade() {
        // TODO: 일 매수 제약조건 추가.
        log.info("trade count = {}", tradeHistoryRepository.countByMarketCodeAndTradeDate("BTC", LocalDate.now()));

        // Fetches the current market price and stores it in the database.
        List<MarketPrice> marketPrices = getMarketPrice();
        if (marketPrices == null) {
            return;
        }

        saveMarketPrice(marketPrices);

        // Get recent price
        List<MarketPrice> recentPrice = getRecentPrices("BTC");

        // Determines whether the conditions for buying are met.
        if (isBuyConditionMet(recentPrice)) {
            buy(recentPrice.get(0));
            telegramService.sendExecutionCompleted(recentPrice);
        }
    }

    public List<MarketPrice> getMarketPrice() {
        // TODO: API를 통해서 가져오도록 변경.
        BufferedReader in = null;
        try {
            URL obj = new URL("https://api.coinone.co.kr/public/v2/trades/KRW/BTC?size=100");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String resultString = in.readLine();
            ObjectMapper objectMapper = new ObjectMapper();
            MarketPriceResponse marketPriceResponse = objectMapper.readValue(resultString, MarketPriceResponse.class);

            BigDecimal averagePrice = marketPriceResponse.getTransactions().stream()
                    .map(transaction -> new BigDecimal(transaction.getPrice()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(new BigDecimal(marketPriceResponse.getTransactions().size()), BigDecimal.ROUND_HALF_UP);

            return List.of(MarketPrice.builder().marketCode("BTC").marketPrice(averagePrice).build());
        } catch (Exception e) {
            log.error("getMarketPrice Err", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    // ignore
                }
            }
        }

        return null;
    }

    public void saveMarketPrice(List<MarketPrice> marketPrices) {
        marketPriceRepository.saveAll(marketPrices);
    }

    public List<MarketPrice> getRecentPrices(String marketCode) {
        return marketPriceRepository.findTop3ByMarketCodeOrderByCreatedAtDesc(marketCode);
    }

    public boolean isBuyConditionMet(List<MarketPrice> recentPrices) {
        for (int i = 0; i < recentPrices.size() - 1; i++) {
            if (recentPrices.get(i).getMarketPrice().compareTo(recentPrices.get(i + 1).getMarketPrice()) <= 0) {
                return false;
            }
        }

        return true;
    }

    public void buy(MarketPrice recentMarketPrice) {
        // TODO: 매수

        // Set trade history.
        TradeHistory tradeHistory = TradeHistory.builder()
                .tradeDate(LocalDate.now())
                .marketCode(recentMarketPrice.getMarketCode())
                .tradePrice(recentMarketPrice.getMarketPrice())
                .build();

        tradeHistoryRepository.save(tradeHistory);
    }

}
