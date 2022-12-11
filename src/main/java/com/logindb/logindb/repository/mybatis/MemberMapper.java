package com.logindb.logindb.repository.mybatis;

import com.logindb.logindb.domain.Member;
import com.logindb.logindb.dto.LoginDto;
import com.logindb.logindb.dto.MemberInsertDto;
import com.logindb.logindb.repository.MemberRepository;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface MemberMapper extends MemberRepository {

    @Results(id = "memberMap", value = {
            @Result(property = "memberNumber", column = "memberId"),
            @Result(property = "id", column = "id"),
            @Result(property = "pwd", column = "pwd")
    })

    //회원 모든 조회(복수)
    @Select("select * from member")
    List<Member> findMemberAll();

    //회원 조회 (단수)
    @Select("select * from member where id = #{id} and pwd = #{pwd}")
    Optional<Member> findMemberByIdAndPwd(LoginDto dto);

    //회원 조회 아이디만 (단수)
    @Select("select id from member where id = #{id}")
    Optional<Member> findMemberById(@Param("id") String id);


    //회원 가입
    @Insert("insert into member(id, pwd) values(#{id}, #{pwd})")
    void insertMember(MemberInsertDto memberInsertDto);

}
