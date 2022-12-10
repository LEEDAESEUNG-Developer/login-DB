package com.logindb.logindb.exception;

public class MemberLoginException extends Exception{

    private static final String message = "회원이 없습니다 회원가입 해주세요.";

    public MemberLoginException() {
        super(message);
    }
}
