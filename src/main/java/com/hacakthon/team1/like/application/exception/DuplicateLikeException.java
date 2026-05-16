package com.hacakthon.team1.like.application.exception;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;

public class DuplicateLikeException extends BusinessException {

    public DuplicateLikeException() {
        super(ErrorCode.DUPLICATE_LIKE);
    }
}
