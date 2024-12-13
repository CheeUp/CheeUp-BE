package com.cheerup.apiPayload;

import com.cheerup.apiPayload.code.BaseCode;
import com.cheerup.apiPayload.code.status.SuccessStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponseDTO<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    // 성공한 경우
    public static <T> ApiResponseDTO<T> onSuccess(T result) {
        return new ApiResponseDTO<>(true, SuccessStatus._OK.getCode(), SuccessStatus._OK.getMessage(), result);
    }

    // 성공한 경우 OK 대신 다른 응답
    public static <T> ApiResponseDTO<T> of(BaseCode code, T result) {
        return new ApiResponseDTO<>(true, code.getReasonHttpStatus().getCode(), code.getReasonHttpStatus().getMessage(), result);
    }

    // 실패한 경우
    public static <T> ApiResponseDTO<T> onFailure(String code, String message, T result) {
        return new ApiResponseDTO<>(false, code, message, result);
    }
}
