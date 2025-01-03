package com.cheeup.apiPayload.exception.handler;

import com.cheeup.apiPayload.code.error.ErrorCode;
import com.cheeup.apiPayload.exception.ServiceException;

public class BadRequestException extends ServiceException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
