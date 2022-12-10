package com.logindb.logindb.exception;

public class MemberCreateException extends Exception{

    private static final String message = "중복회원입니다.";

    public MemberCreateException() {
        super(message);
    }
}
