package com.logindb.logindb.service;

import com.logindb.logindb.dto.MemberInsertDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Test
    void 회원가입_중복회원() {
        MemberInsertDto memberInsertDto = new MemberInsertDto();
        memberInsertDto.setId("aaaa");
        memberInsertDto.setPwd("1234");

        memberService.createMember(memberInsertDto);
    }


}