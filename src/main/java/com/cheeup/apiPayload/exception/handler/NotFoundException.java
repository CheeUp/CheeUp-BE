package com.cheeup.apiPayload.exception.handler;

import com.cheeup.apiPayload.code.error.ErrorCode;
import com.cheeup.apiPayload.exception.ServiceException;

public class NotFoundException extends ServiceException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
