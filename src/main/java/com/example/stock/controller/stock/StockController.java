package com.example.stock.controller.stock;

import com.example.stock.domain.stock.StockReq;
import com.example.stock.domain.stock.StockRes;
import com.example.stock.service.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
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
    public ResponseEntity<StockRes> saveStock(@RequestBody StockReq stockReq) {
        return ResponseEntity.ok(stockService.save(stockReq));
    }

    @PutMapping("/api/stocks/{stockId}")
    public ResponseEntity<StockRes> editStock(@PathVariable Long stockId, @RequestBody StockReq stockReq) {
        return ResponseEntity.ok(stockService.edit(stockId, stockReq));
    }

    @DeleteMapping("/api/stocks/{stockId}")
    public ResponseEntity<Long> removeStock(@PathVariable Long stockId) {
        stockService.remove(stockId);
        return ResponseEntity.ok(stockId);
    }

}
