package com.example.stock.common.scheduled;

import com.example.stock.domain.stock.Stock;
import com.example.stock.repository.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ScheduledTasks {

    private final StockRepository stockRepository;

    @Scheduled(cron = "0 0 1 * * *")
    public void deleteStock() {
        LocalDate today = LocalDate.now();
        List<Stock> stocks = stockRepository.findAll();
        for (Stock stock : stocks) {
            if (today.isAfter(stock.getListingDate())) {
                log.info("상장된 항목 제거 : {}", stock);
                stockRepository.delete(stock);
            }
        }
    }

}
