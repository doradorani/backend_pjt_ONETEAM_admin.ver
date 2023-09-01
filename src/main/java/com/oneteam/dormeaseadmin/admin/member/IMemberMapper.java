package com.oneteam.dormeaseadmin.admin.member;

import com.oneteam.dormeaseadmin.admin.leavePass.LeavePassDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMemberMapper {

    boolean selectDuplicateByID(String id);
    int createAccountConfirm(MemberDto adminDto);
    MemberDto loginAccountConfirm(String id);
    List<LeavePassDto> selectLeavePassList(String schoolNo);
}
