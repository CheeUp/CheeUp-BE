package com.cheeup.apiPayload.exception.handler;

import com.cheeup.apiPayload.code.error.codes.JobErrorCode;
import com.cheeup.apiPayload.exception.ServiceException;

public class JobException extends ServiceException {
    public JobException(JobErrorCode jobErrorCode) {
        super(jobErrorCode);
    }
}
