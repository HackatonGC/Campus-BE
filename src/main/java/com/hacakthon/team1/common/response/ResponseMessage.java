package com.hacakthon.team1.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {

    OK(200, "요청이 성공했습니다."),
    CREATED(201, "생성되었습니다."),

    USER_SIGNUP(201, "회원가입이 완료되었습니다."),
    USER_LOGIN(200, "로그인이 완료되었습니다."),
    USER_LOGOUT(200, "로그아웃이 완료되었습니다."),
    USER_FOUND(200, "유저 정보 조회에 성공했습니다."),
    USER_UPDATED(200, "프로필이 수정되었습니다."),
    USER_DELETED(200, "회원탈퇴가 완료되었습니다."),

    PROJECT_CREATED(201, "프로젝트가 등록되었습니다."),
    PROJECT_FOUND(200, "프로젝트 조회에 성공했습니다."),
    PROJECT_LIST_FOUND(200, "프로젝트 목록 조회에 성공했습니다."),
    PROJECT_DELETED(200, "프로젝트가 삭제되었습니다."),

    APPLICATION_CREATED(201, "팀원 신청이 완료되었습니다."),
    APPLICATION_STATUS_UPDATED(200, "신청 상태가 변경되었습니다."),
    APPLICATION_CANCELED(200, "신청이 취소되었습니다."),
    APPLICATION_LIST_FOUND(200, "신청 목록 조회에 성공했습니다."),

    REVIEW_REQUEST_CREATED(201, "코드 리뷰 요청이 완료되었습니다."),
    REVIEW_REQUEST_FOUND(200, "코드 리뷰 요청 조회에 성공했습니다."),
    REVIEW_REQUEST_LIST_FOUND(200, "코드 리뷰 요청 목록 조회에 성공했습니다.");

    private final int status;
    private final String message;
}
