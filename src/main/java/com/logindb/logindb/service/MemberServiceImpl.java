package com.logindb.logindb.service;

import com.logindb.logindb.domain.Member;
import com.logindb.logindb.dto.LoginDto;
import com.logindb.logindb.dto.MemberInsertDto;
import com.logindb.logindb.exception.MemberCreateException;
import com.logindb.logindb.exception.MemberLoginException;
import com.logindb.logindb.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void createMember(MemberInsertDto dto) {
        // 중복 회원 검사
        Optional<Member> memberById = memberRepository.findMemberById(dto.getId());
        if(memberById.isEmpty()) memberRepository.insertMember(dto);
    }

    @Override
    public Member login(LoginDto dto){
        try{
            return memberRepository.findMemberByIdAndPwd(dto).orElseThrow(MemberLoginException::new);
        } catch(MemberLoginException e){
            log.debug(e.getMessage());
            return new Member();
        }
    }
}
