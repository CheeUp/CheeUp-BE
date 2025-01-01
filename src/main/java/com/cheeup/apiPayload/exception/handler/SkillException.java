package com.cheeup.apiPayload.exception.handler;

import com.cheeup.apiPayload.code.error.codes.SkillErrorCode;
import com.cheeup.apiPayload.exception.ServiceException;

public class SkillException extends ServiceException {
    public SkillException(SkillErrorCode skillErrorCode) {
        super(skillErrorCode);
    }
}
