package com.example.stock.controller.stock;

import com.example.stock.domain.common.CommonRes;
import com.example.stock.domain.stock.StockReq;
import com.example.stock.service.stock.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "공모주 API", description = "공모주...")
public class StockController {

    private final StockService stockService;

    @GetMapping("/api/stocks")
    @Operation(summary = "공모주 리스트 조회", description = "공모주 리스트를 조회한다.")
    public ResponseEntity<CommonRes> findStocks() {
        return ResponseEntity.ok(CommonRes.successRes(stockService.findStocks()));
    }

    @GetMapping("/api/stocks/{stockId}")
    @Operation(summary = "공모주 상세 조회", description = "공모주 상세 정보를 조회한다.")
    public ResponseEntity<CommonRes> findStock(@PathVariable Long stockId) {
        return ResponseEntity.ok(CommonRes.successRes(stockService.findStock(stockId)));
    }

    @PostMapping("/api/stocks")
    @Operation(summary = "공모주 등록", description = "공모주 정보를 등록한다.")
    public ResponseEntity<CommonRes> saveStock(@RequestBody StockReq stockReq) {
        return ResponseEntity.ok(CommonRes.successRes(stockService.save(stockReq)));
    }

    @PutMapping("/api/stocks/{stockId}")
    @Operation(summary = "공모주 수정", description = "공모주 정보를 수정한다.")
    public ResponseEntity<CommonRes> editStock(@PathVariable Long stockId, @RequestBody StockReq stockReq) {
        return ResponseEntity.ok(CommonRes.successRes(stockService.edit(stockId, stockReq)));
    }

    @DeleteMapping("/api/stocks/{stockId}")
    @Operation(summary = "공모주 삭제", description = "공모주 정보를 삭제한다.")
    public ResponseEntity<CommonRes> removeStock(@PathVariable Long stockId) {
        stockService.remove(stockId);
        return ResponseEntity.ok(CommonRes.successRes(stockId));
    }

}
