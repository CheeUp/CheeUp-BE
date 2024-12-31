package com.cheeup.apiPayload.exception.handler;

import com.cheeup.apiPayload.code.error.codes.JobNoticeErrorCode;
import com.cheeup.apiPayload.exception.ServiceException;

public class JobNoticeException extends ServiceException {
    public JobNoticeException(JobNoticeErrorCode jobNoticeErrorCode) {
        super(jobNoticeErrorCode);
    }
}
