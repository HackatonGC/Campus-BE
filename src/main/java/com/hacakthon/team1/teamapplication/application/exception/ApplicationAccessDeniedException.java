package com.hacakthon.team1.teamapplication.application.exception;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;

public class ApplicationAccessDeniedException extends BusinessException {

    public ApplicationAccessDeniedException() {
        super(ErrorCode.APPLICATION_ACCESS_DENIED);
    }
}
