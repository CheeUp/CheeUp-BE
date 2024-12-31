package com.cheeup.apiPayload;

import com.cheeup.apiPayload.code.error.ErrorCode;
import com.cheeup.apiPayload.code.success.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private final HttpStatus httpStatus;
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    private T result;

    public static <T> ApiResponse<T> onSuccess(SuccessCode successCode, T result) {
        return new ApiResponse<>(successCode.getHttpStatus(), false, successCode.getCode(), successCode.getMessage(), result);
    }

    public static <T> ApiResponse<T> onFailure(ErrorCode errorCode){
        return new ApiResponse<>(errorCode.getHttpStatus(), false, errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * withoutHttpStatus 는 필요하면 여기에 구현
     */

}
