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
import java.util.Optional;

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
    void findMemberById_O(){
        LoginDto loginDto = new LoginDto();

        loginDto.setId("aaaa");
        loginDto.setPwd("1234");

        Optional<Member> memberById = memberMapper.findMemberByIdAndPwd(loginDto);
        System.out.println("memberById = " + memberById);
        Assertions.assertThat(memberById.isEmpty()).isFalse();
    }

    @DisplayName("회원 아이디 조회 안됐을때")
    @Test
    void findMemberById_X(){
        LoginDto loginDto = new LoginDto();

        loginDto.setId(null);
        loginDto.setPwd(null);

        Optional<Member> memberById = memberMapper.findMemberByIdAndPwd(loginDto);
        Assertions.assertThat(memberById.isEmpty()).isTrue();
    }

    @DisplayName("회원가입")
    @Test
    void register(){
        // 회원가입
        MemberInsertDto memberInsertDto = new MemberInsertDto();

        memberInsertDto.setId("bbbb");
        memberInsertDto.setPwd("1234");

        memberMapper.insertMember(memberInsertDto);

        // 로그인
        LoginDto loginDto = new LoginDto();

        loginDto.setId(memberInsertDto.getId());
        loginDto.setPwd(memberInsertDto.getPwd());

        Member memberByIdAndPwd = memberMapper.findMemberByIdAndPwd(loginDto).get();

        Assertions.assertThat(memberByIdAndPwd.getId()).isEqualTo(loginDto.getId());
    }

}