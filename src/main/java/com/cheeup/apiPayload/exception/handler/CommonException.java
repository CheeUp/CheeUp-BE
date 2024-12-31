package com.cheeup.apiPayload.exception.handler;

import com.cheeup.apiPayload.code.error.codes.CommonErrorCode;
import com.cheeup.apiPayload.exception.ServiceException;

public class CommonException extends ServiceException {
    public CommonException(CommonErrorCode commonErrorCode) {
        super(commonErrorCode);
    }
}
