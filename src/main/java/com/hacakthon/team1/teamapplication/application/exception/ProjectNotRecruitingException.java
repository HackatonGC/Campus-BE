package com.hacakthon.team1.teamapplication.application.exception;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;

public class ProjectNotRecruitingException extends BusinessException {

    public ProjectNotRecruitingException() {
        super(ErrorCode.PROJECT_NOT_RECRUITING);
    }
}
