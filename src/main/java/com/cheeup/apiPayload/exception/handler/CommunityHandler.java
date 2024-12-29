package com.cheeup.apiPayload.exception.handler;

import com.cheeup.apiPayload.code.BaseErrorCode;
import com.cheeup.apiPayload.exception.GeneralException;

public class CommunityHandler extends GeneralException {
    public CommunityHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
