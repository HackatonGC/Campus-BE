package com.hacakthon.team1.bookmark.application.exception;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;

public class DuplicateBookmarkException extends BusinessException {

    public DuplicateBookmarkException() {
        super(ErrorCode.DUPLICATE_BOOKMARK);
    }
}
