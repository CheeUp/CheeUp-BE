package com.cheeup.apiPayload.code.success;

import org.springframework.http.HttpStatus;

public interface SuccessCode {
    public HttpStatus getHttpStatus();
    public String getCode();
    public String getMessage();

}
