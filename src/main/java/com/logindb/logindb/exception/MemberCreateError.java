package com.logindb.logindb.exception;

public class MemberCreateError extends Exception{

    private static final String message = "중복회원입니다.";

    public MemberCreateError() {
        super(message);
    }
}
