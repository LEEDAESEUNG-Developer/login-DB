package com.logindb.logindb.service;

import com.logindb.logindb.domain.Member;
import com.logindb.logindb.dto.LoginDto;
import com.logindb.logindb.dto.MemberInsertDto;
import com.logindb.logindb.exception.MemberCreateException;
import com.logindb.logindb.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void createMember(MemberInsertDto dto) {
        // 회원 검사
        try {
            Member memberById = memberRepository.findMemberById(dto.getId());
            if (memberById != null) throw new MemberCreateException();
            memberRepository.insertMember(dto);
        } catch (MemberCreateException e) {
            log.debug(e.getMessage());
        }
    }

    @Override
    public Member login(LoginDto dto) {
        return memberRepository.findMemberByIdAndPwd(dto);
    }
}
