package com.cheeup.apiPayload.exception.handler;

import com.cheeup.apiPayload.code.error.codes.MemberErrorCode;
import com.cheeup.apiPayload.exception.ServiceException;

public class MemberException extends ServiceException {
    public MemberException(MemberErrorCode code) {
        super(code);
    }
}
