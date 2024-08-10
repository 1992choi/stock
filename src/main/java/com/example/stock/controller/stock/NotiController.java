package com.example.stock.controller.stock;

import com.example.stock.domain.common.CommonRes;
import com.example.stock.service.noti.TelegramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Tag(name = "공모주 알림 API", description = "공모주...")
public class NotiController {

    private final TelegramService telegramService;

    @GetMapping("/api/noti/stocks")
    @Operation(summary = "공모주 알림", description = "공모주 알림 발송")
    public ResponseEntity<CommonRes> findStocks() throws IOException {
        telegramService.sendMessage("Subscription");
        return ResponseEntity.ok(CommonRes.successRes("발송 성공"));
    }

}
