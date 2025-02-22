package com.example.stock.service.market;

import com.example.stock.domain.market.MarketPrice;
import com.example.stock.repository.market.MarketPriceRepository;
import com.example.stock.service.noti.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketService {

    private final Random random = new Random();
    private final MarketPriceRepository marketPriceRepository;
    private final TelegramService telegramService;

    public void executeTrade() {
        // Fetches the current market price and stores it in the database.
        saveMarketPrice(getMarketPrice());

        // Get recent price
        List<MarketPrice> recentPrice = getRecentPrices("BTC");

        // Determines whether the conditions for buying are met.
        if (isBuyConditionMet(recentPrice)) {
            buy();
            telegramService.sendExecutionCompleted(recentPrice);
        }
    }

    public List<MarketPrice> getMarketPrice() {
        // TODO: API를 통해서 가져오도록 변경.
        BigDecimal price1 = getRandomDecimal(1000, 1100);
        BigDecimal price2 = getRandomDecimal(100, 110);
        BigDecimal price3 = getRandomDecimal(100, 110);

        return List.of(
                MarketPrice.builder().marketCode("BTC").marketPrice(price1).build(),
                MarketPrice.builder().marketCode("ETH").marketPrice(price2).build(),
                MarketPrice.builder().marketCode("XRP").marketPrice(price3).build()
        );
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

    public void buy() {
        // TODO: 매수
    }

    // TODO: API를 통해 가격을 가져오게 되면 해당 함수 제거 필요
    private BigDecimal getRandomDecimal(double min, double max) {
        double randomValue = min + (max - min) * random.nextDouble();
        return BigDecimal.valueOf(randomValue).setScale(2, RoundingMode.HALF_UP);
    }

}
