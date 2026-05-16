package com.hacakthon.team1.teamapplication.application.exception;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;

public class DuplicateApplicationException extends BusinessException {

    public DuplicateApplicationException() {
        super(ErrorCode.DUPLICATE_APPLICATION);
    }
}
