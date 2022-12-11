package com.logindb.logindb.repository;

import com.logindb.logindb.domain.Member;
import com.logindb.logindb.dto.LoginDto;
import com.logindb.logindb.dto.MemberInsertDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    //회원 모든 조회(복수)
    List<Member> findMemberAll();

    //회원 조회
    Optional<Member> findMemberByIdAndPwd(LoginDto LoginDto);

    //회원 조회 아이디만 (단수)
    Optional<Member> findMemberById(String id);

    //회원 가입
    void insertMember(MemberInsertDto memberInsertDto);

}
