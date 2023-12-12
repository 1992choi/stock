package com.example.stock.domain.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonRes<T> {

    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";

    @Schema(description = "상태 코드(success / fail)", nullable = false, example = "fail")
    private String status;

    @Schema(description = "응답 메시지", nullable = false, example = "존재하지 않는 정보입니다.")
    private String message;

    @Schema(description = "응답 데이터", nullable = false)
    private T data;

    public static CommonRes<?> successRes(String message) {
        return new CommonRes<>(SUCCESS_STATUS, message, null);
    }

    public static <T> CommonRes<T> successRes(T data) {
        return new CommonRes<>(SUCCESS_STATUS, "", data);
    }

    public static <T> CommonRes<T> successRes(String message, T data) {
        return new CommonRes<>(SUCCESS_STATUS, message, data);
    }

    public static CommonRes<?> failRes(String message) {
        return new CommonRes<>(FAIL_STATUS, message, null);
    }

    private CommonRes(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
