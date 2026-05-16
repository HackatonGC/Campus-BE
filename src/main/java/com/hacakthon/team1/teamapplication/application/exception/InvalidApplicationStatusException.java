package com.hacakthon.team1.teamapplication.application.exception;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;

public class InvalidApplicationStatusException extends BusinessException {

    public InvalidApplicationStatusException() {
        super(ErrorCode.APPLICATION_INVALID_STATUS);
    }
}
