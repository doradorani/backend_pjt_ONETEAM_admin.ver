package com.oneteam.dormeaseadmin.admin.leavePass;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class LeavePassService {

    private final ILeavePassMapper leavePassMapper;

    public LeavePassService(ILeavePassMapper leavePassMapper){
        this.leavePassMapper = leavePassMapper;
    }

    public int modifyLeavePassConfirm(LeavePassDto leavePassDto) {
        log.info("modifyLeavePassConfirm()");

        int result = leavePassMapper.updateLeavePass(leavePassDto);

        return 0;
    }

    public List<LeavePassDto> leaveOutList(String schoolNo) {
        log.info("leaveOutList()");

        return leavePassMapper.selectLeavePassList(schoolNo);
    }
    public List<LeavePassDto> approveLeavePass(int no, String schoolNo) {
        log.info("approveLeavePass()");
        leavePassMapper.updateApproveLeavePass(no);

        return leavePassMapper.selectLeavePassList(schoolNo);
    }

    public LeavePassDto modifyLeavePassForm(int no) {
        log.info("modifyLeavePassForm()");
        LeavePassDto leavePassDto = leavePassMapper.selectLeavePassByNo(no);

        return leavePassDto;
    }
}