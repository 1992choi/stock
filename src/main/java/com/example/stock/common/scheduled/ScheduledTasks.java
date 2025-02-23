package com.example.stock.common.scheduled;

import com.example.stock.domain.stock.BookmarkRes;
import com.example.stock.domain.stock.Stock;
import com.example.stock.domain.user.UserRes;
import com.example.stock.repository.stock.StockRepository;
import com.example.stock.service.noti.SlackService;
import com.example.stock.service.noti.TelegramService;
import com.example.stock.service.market.MarketService;
import com.example.stock.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ScheduledTasks {

    private final UserService userService;
    private final MarketService marketService;
    private final StockRepository stockRepository;
    private final SlackService slackService;
    private final TelegramService telegramService;

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
            String title = userRes.getUserName() + "님 공모주 알림입니다.";
            List<String> stocks = new ArrayList<>();
            for (BookmarkRes bookmarkRes : userRes.getBookmarks()) {
                if (today.isEqual(bookmarkRes.getStock().getSubscriptDate())) {
                    stocks.add(bookmarkRes.getStock().getStockName());
                }
            }
            if (stocks.size() != 0) {
                slackService.sendMessage(title, stocks);
            }
        }
    }

    @Scheduled(cron = "0 50 8 * * *")
    public void alertListing() {
        LocalDate today = LocalDate.now();
        List<UserRes> users = userService.findUsers();
        for (UserRes userRes : users) {
            String title = userRes.getUserName() + "님 상장일 알림입니다.";
            List<String> stocks = new ArrayList<>();
            for (BookmarkRes bookmarkRes : userRes.getBookmarks()) {
                if (today.isEqual(bookmarkRes.getStock().getListingDate()) && "Y".equals(bookmarkRes.getIssuedFlag())) {
                    stocks.add(bookmarkRes.getStock().getStockName());
                }
            }
            if (stocks.size() != 0) {
                slackService.sendMessage(title, stocks);
            }
        }
    }

    @Scheduled(cron = "0 30 8 * * *")
    public void alertIpoUsingTelegram() throws Exception {
        telegramService.sendMessage("IPO");
    }

    @Scheduled(cron = "0 30 8 * * *")
    public void alertSubscriptionUsingTelegram() throws Exception {
        telegramService.sendMessage("Subscription");
    }

    @Scheduled(cron = "0 0 15 * * *")
    public void alertSubscriptionClosingUsingTelegram() throws Exception {
        telegramService.sendMessage("Closing");
    }

    @Scheduled(cron = "0 * * * * *")
    public void executeBuy() {
        marketService.executeBuy();
    }

    @Scheduled(cron = "0 * * * * *")
    public void executeSell() {
        marketService.executeSell();
    }

}
