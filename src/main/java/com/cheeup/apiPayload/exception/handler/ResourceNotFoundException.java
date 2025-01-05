package com.cheeup.apiPayload.exception.handler;

import com.cheeup.apiPayload.code.error.ErrorCode;
import com.cheeup.apiPayload.exception.ServiceException;

public class ResourceNotFoundException extends ServiceException {
    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
