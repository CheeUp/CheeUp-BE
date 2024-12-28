package com.cheeup.apiPayload.exception.handler;

import com.cheeup.apiPayload.code.BaseErrorCode;
import com.cheeup.apiPayload.exception.GeneralException;

public class JobNoticeHandler extends GeneralException {
    public JobNoticeHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
