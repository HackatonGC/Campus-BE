package com.hacakthon.team1.reviewrequest.application.exception;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;

public class ReviewRequestNotFoundException extends BusinessException {

    public ReviewRequestNotFoundException() {
        super(ErrorCode.REVIEW_REQUEST_NOT_FOUND);
    }
}
