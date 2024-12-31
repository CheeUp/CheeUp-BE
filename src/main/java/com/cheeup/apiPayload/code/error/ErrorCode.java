package com.cheeup.apiPayload.code.error;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    public HttpStatus getHttpStatus();
    public String getCode();
    public String getMessage();

}
