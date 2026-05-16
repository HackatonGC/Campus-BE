package com.hacakthon.team1.teamapplication.application.exception;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;

public class InvalidRecruitmentException extends BusinessException {

    public InvalidRecruitmentException() {
        super(ErrorCode.INVALID_RECRUITMENT);
    }
}
