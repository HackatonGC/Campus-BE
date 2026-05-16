package com.hacakthon.team1.presentation.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResponse<T> {

    private final int status;
    private final String message;
    private final T data;

    public static <T> CommonResponse<T> success(ResponseMessage responseMessage, T data) {
        return new CommonResponse<>(responseMessage.getStatus(), responseMessage.getMessage(), data);
    }

    public static <T> CommonResponse<T> success(ResponseMessage responseMessage) {
        return new CommonResponse<>(responseMessage.getStatus(), responseMessage.getMessage(), null);
    }

    public static <T> CommonResponse<T> fail(int status, String message) {
        return new CommonResponse<>(status, message, null);
    }
}
