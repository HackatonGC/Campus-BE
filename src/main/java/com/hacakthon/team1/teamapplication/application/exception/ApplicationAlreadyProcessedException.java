package com.hacakthon.team1.teamapplication.application.exception;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;

public class ApplicationAlreadyProcessedException extends BusinessException {

    public ApplicationAlreadyProcessedException() {
        super(ErrorCode.APPLICATION_ALREADY_PROCESSED);
    }
}
