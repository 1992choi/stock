package com.example.stock.common.scheduled;

import com.example.stock.domain.stock.BookmarkRes;
import com.example.stock.domain.stock.Stock;
import com.example.stock.domain.user.UserRes;
import com.example.stock.repository.stock.StockRepository;
import com.example.stock.service.user.UserService;
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

    private final UserService userService;
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

    @Scheduled(cron = "0 0 10 * * *")
    public void alertSubscript() {
        LocalDate today = LocalDate.now();
        List<UserRes> users = userService.findUsers();
        for (UserRes userRes : users) {
            for (BookmarkRes bookmarkRes : userRes.getBookmarks()) {
                if (today.isEqual(bookmarkRes.getStock().getSubscriptDate())) {
                    log.info("{}님에게 {} 공모주 알림", userRes.getUserName(), bookmarkRes.getStock().getStockName());
                }
            }
        }
    }

    @Scheduled(cron = "0 50 8 * * *")
    public void alertListing() {
        LocalDate today = LocalDate.now();
        List<UserRes> users = userService.findUsers();
        for (UserRes userRes : users) {
            for (BookmarkRes bookmarkRes : userRes.getBookmarks()) {
                if (today.isEqual(bookmarkRes.getStock().getListingDate()) && "Y".equals(bookmarkRes.getIssuedFlag())) {
                    log.info("{}님에게 {} 상장 알림", userRes.getUserName(), bookmarkRes.getStock().getStockName());
                }
            }
        }
    }

}
