package com.hacakthon.team1.auth.application.exception;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;

public class InvalidVerificationCodeException extends BusinessException {

    public InvalidVerificationCodeException() {
        super(ErrorCode.INVALID_VERIFICATION_CODE);
    }
}
