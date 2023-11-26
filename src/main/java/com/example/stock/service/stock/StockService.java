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

}
