package com.logindb.logindb.service;

import com.logindb.logindb.domain.Member;
import com.logindb.logindb.dto.LoginDto;
import com.logindb.logindb.dto.MemberInsertDto;
import com.logindb.logindb.exception.MemberLoginException;

public interface MemberService {

    // 회원가입
    void createMember(MemberInsertDto dto);

    // 로그인
    Member login(LoginDto dto);

}
