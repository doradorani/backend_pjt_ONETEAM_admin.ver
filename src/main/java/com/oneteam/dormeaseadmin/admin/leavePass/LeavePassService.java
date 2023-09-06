package com.oneteam.dormeaseadmin.admin.leavePass;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Map;

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

        return result;
    }

    public Map<String, Object> leaveOutList(String schoolNo, int pageNum, int amount) {
        log.info("approveLeavePass()");
        return CommonLeavePass.commonClass(schoolNo, pageNum, amount );
    }
    public Map<String, Object> approveLeavePass(int no, String schoolNo, int pageNum, int amount) {
        log.info("approveLeavePass()");
        leavePassMapper.updateApproveLeavePass(no);

        return CommonLeavePass.commonClass(schoolNo, pageNum, amount);
    }

    public LeavePassDto modifyLeavePassForm(int no) {
        log.info("modifyLeavePassForm()");
        LeavePassDto leavePassDto = leavePassMapper.selectLeavePassByNo(no);

        return leavePassDto;
    }

    public Map<String, Object> modifyComebackDateConfirm(LeavePassDto leavePassDto, int pageNum, int amount) {
        log.info("modifyComebackDateConfirm()");
        leavePassMapper.updateLeavePass(leavePassDto);
        return CommonLeavePass.commonClass(leavePassDto.getSchool_no(), pageNum, amount);
    }


}