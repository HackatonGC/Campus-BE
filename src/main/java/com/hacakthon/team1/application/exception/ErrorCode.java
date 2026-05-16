package com.hacakthon.team1.application.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_INPUT(HttpStatus.BAD_REQUEST, "잘못된 입력입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다."),
    DUPLICATE_APPLICATION(HttpStatus.CONFLICT, "이미 신청한 프로젝트입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 리소스입니다."),
    APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 신청입니다."),
    APPLICATION_ALREADY_PROCESSED(HttpStatus.CONFLICT, "이미 처리된 신청입니다."),
    APPLICATION_INVALID_STATUS(HttpStatus.BAD_REQUEST, "유효하지 않은 신청 상태입니다."),
    APPLICATION_ACCESS_DENIED(HttpStatus.FORBIDDEN, "본인의 신청만 취소할 수 있습니다."),
    PROJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 프로젝트입니다."),
    DUPLICATE_BOOKMARK(HttpStatus.CONFLICT, "이미 북마크한 프로젝트입니다."),
    BOOKMARK_NOT_FOUND(HttpStatus.NOT_FOUND, "북마크를 찾을 수 없습니다."),
    DUPLICATE_LIKE(HttpStatus.CONFLICT, "이미 좋아요한 프로젝트입니다."),
    LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "좋아요를 찾을 수 없습니다."),
    PROJECT_NOT_RECRUITING(HttpStatus.BAD_REQUEST, "모집 중인 프로젝트가 아닙니다."),
    REVIEW_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 코드 리뷰 요청입니다."),
    REVIEW_ANSWER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 코드 리뷰 답변입니다."),
    INVALID_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, "인증 코드가 올바르지 않거나 만료되었습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String message;
}
