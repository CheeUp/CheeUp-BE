package com.cheeup.apiPayload.exception.handler;

import com.cheeup.apiPayload.code.BaseErrorCode;
import com.cheeup.apiPayload.exception.GeneralException;

public class CommonHandler extends GeneralException {
    public CommonHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
