package com.example.stock.controller.stock;

import com.example.stock.domain.stock.Stock;
import com.example.stock.domain.stock.StockRes;
import com.example.stock.service.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class StockController {

    private final StockService stockService;

    @GetMapping("/api/stocks")
    public ResponseEntity<List<StockRes>> findStocks() {
        return ResponseEntity.ok(stockService.findStocks());
    }

    @GetMapping("/api/stocks/{stockId}")
    public ResponseEntity<StockRes> findStock(@PathVariable Long stockId) {
        return ResponseEntity.ok(stockService.findStock(stockId));
    }

    @PostMapping("/api/stocks")
    public ResponseEntity<StockRes> saveStock(@RequestBody Stock stock) {
        return ResponseEntity.ok(stockService.save(stock));
    }

}
