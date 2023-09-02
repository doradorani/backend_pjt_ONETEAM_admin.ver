package com.oneteam.dormeaseadmin.admin.leavePass;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ILeavePassMapper {
    void updateApproveLeavePass(int no);
    List<LeavePassDto> selectLeavePassList(String schoolNo);

    LeavePassDto selectLeavePassByNo(int no);

    int updateLeavePass(LeavePassDto leavePassDto);

    void updateLeavePassByNo(int no);

    List<LeavePassDto> selectLeavePassBySchoolNo(String schoolNo);
}
