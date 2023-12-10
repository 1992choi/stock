package com.example.stock.controller.stock;

import com.example.stock.domain.common.CommonRes;
import com.example.stock.domain.stock.StockReq;
import com.example.stock.service.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/api/stocks")
    public ResponseEntity<CommonRes> findStocks() {
        return ResponseEntity.ok(CommonRes.successRes(stockService.findStocks()));
    }

    @GetMapping("/api/stocks/{stockId}")
    public ResponseEntity<CommonRes> findStock(@PathVariable Long stockId) {
        return ResponseEntity.ok(CommonRes.successRes(stockService.findStock(stockId)));
    }

    @PostMapping("/api/stocks")
    public ResponseEntity<CommonRes> saveStock(@RequestBody StockReq stockReq) {
        return ResponseEntity.ok(CommonRes.successRes(stockService.save(stockReq)));
    }

    @PutMapping("/api/stocks/{stockId}")
    public ResponseEntity<CommonRes> editStock(@PathVariable Long stockId, @RequestBody StockReq stockReq) {
        return ResponseEntity.ok(CommonRes.successRes(stockService.edit(stockId, stockReq)));
    }

    @DeleteMapping("/api/stocks/{stockId}")
    public ResponseEntity<CommonRes> removeStock(@PathVariable Long stockId) {
        stockService.remove(stockId);
        return ResponseEntity.ok(CommonRes.successRes(stockId));
    }

}
