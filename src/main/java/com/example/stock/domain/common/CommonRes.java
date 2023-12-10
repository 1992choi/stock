package com.example.stock.domain.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonRes<T> {

    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final String ERROR_STATUS = "error";

    private String status;
    private String message;
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

    public static CommonRes<?> errorRes(String message) {
        return new CommonRes<>(ERROR_STATUS, message, null);
    }

    private CommonRes(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
