package com.example.stock.service.stock;

import com.example.stock.domain.stock.Stock;
import com.example.stock.domain.stock.StockRes;
import com.example.stock.repository.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;

    public List<StockRes> findStocks() {
        return stockRepository.findAll().stream()
                .map(StockRes::new)
                .collect(Collectors.toList());
    }

    public StockRes findStock(Long stockId) {
        return new StockRes(stockRepository.findById(stockId).orElseGet(Stock::new));
    }

    @Transactional
    public StockRes save(Stock stock) {
        return new StockRes(stockRepository.save(stock));
    }

}
