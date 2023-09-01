package com.oneteam.dormeaseadmin.admin.leavePass;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ILeavePassMapper {
    void updateApproveLeavePass(int no);
    List<LeavePassDto> selectLeavePassList(String schoolNo);

}
