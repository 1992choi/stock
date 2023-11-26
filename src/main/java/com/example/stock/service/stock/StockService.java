package com.example.stock.service.stock;

import com.example.stock.domain.stock.Stock;
import com.example.stock.repository.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;

    public List<Stock> findStocks() {
        return stockRepository.findAll();
    }

    public Stock findStock(Long stockId) {
        return stockRepository.findById(stockId).orElseGet(Stock::new);
    }

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

}
