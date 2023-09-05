package com.oneteam.dormeaseadmin.admin.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMemberMapper {

    boolean selectDuplicateByID(String id);
    int createAccountConfirm(MemberDto adminDto);
    void insertAdminLoginHistory(MemberDto loginedMemberDto);

    MemberDto selectMemberByID(String id);

    void updateFailCount(MemberDto memberDto);

    int selectMaxNoFromLoginHistory(String id);

    void updateAdminLoginHistory(int maxNo);
}
