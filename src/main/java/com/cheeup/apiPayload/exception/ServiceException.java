package com.cheeup.apiPayload.exception;

import com.cheeup.apiPayload.code.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {

    // Enum으로 관리하는 것을 강제하기 위해 enum 타입의 필드로 관리
    private final ErrorCode errorCode;

    public ServiceException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatus(){
        return errorCode.getHttpStatus();
    }

    public String getMessage(){
        return errorCode.getMessage();
    }

    public String getCode(){
        return errorCode.getCode();
    }

}
