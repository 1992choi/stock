package com.example.stock.controller.stock;

import com.example.stock.domain.stock.Stock;
import com.example.stock.service.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StockController {

    private final StockService stockService;

    @GetMapping("/api/stocks")
    public ResponseEntity<List<Stock>> findStocks() {
        return ResponseEntity.ok(stockService.findStocks());
    }

}
