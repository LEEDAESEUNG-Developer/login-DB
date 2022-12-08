package com.logindb.logindb.repository.mybatis;

import com.logindb.logindb.domain.Member;
import com.logindb.logindb.dto.LoginDto;
import com.logindb.logindb.dto.MemberInsertDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @DisplayName("회원 모두 조회")
    @Test
    void findAllMember(){
        List<Member> memberAll = memberMapper.findMemberAll();

        System.out.println("memberAll = " + memberAll);
    }

    @DisplayName("회원 아이디 조회")
    @Test
    void findMemberById(){
        LoginDto loginDto = new LoginDto();

        loginDto.setId("aaaa");
        loginDto.setPwd("1234");

        Member memberById = memberMapper.findMemberByIdAndPwd(loginDto);
        System.out.println("memberById = " + memberById);
    }

    @DisplayName("회원가입")
    @Test
    void register(){
        MemberInsertDto memberInsertDto = new MemberInsertDto();

        memberInsertDto.setId("bbbb");
        memberInsertDto.setPwd("1234");

        memberMapper.insertMember(memberInsertDto);

        LoginDto loginDto = new LoginDto();

        loginDto.setId("bbbb");
        loginDto.setPwd("1234");

        Member memberById = memberMapper.findMemberByIdAndPwd(loginDto);

        Assertions.assertThat(memberInsertDto.getId()).isEqualTo(loginDto.getId());
    }

}