package com.example.stock.service.stock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketService {

    public void executeTrade() {
        log.info("executeTrade");
        getMarketPrice();
        saveMarketPrice();
        comparePrice();
        buy();
    }

    public void getMarketPrice() {
        // TODO: API를 통해서 현재 가격 가져오기.
    }

    public void saveMarketPrice() {
        // TODO: 현재 가격 DB에 저장
    }

    public void comparePrice() {
        // TODO: 최근 n분의 데이터 비교
    }

    public void buy() {
        // TODO: 매수
    }

}
