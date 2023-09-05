package com.oneteam.dormeaseadmin.admin.leavePass;

import com.oneteam.dormeaseadmin.utils.pagination.Criteria;
import com.oneteam.dormeaseadmin.utils.pagination.PageMakerDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
        return commonClass(0, schoolNo, pageNum, amount );
    }
    public Map<String, Object> approveLeavePass(int no, String schoolNo, int pageNum, int amount) {
        log.info("approveLeavePass()");
        leavePassMapper.updateLeavePassByNo(no);

        return commonClass(no, schoolNo, pageNum, amount);
    }

    public LeavePassDto modifyLeavePassForm(int no) {
        log.info("modifyLeavePassForm()");
        LeavePassDto leavePassDto = leavePassMapper.selectLeavePassByNo(no);

        return leavePassDto;
    }

    public Map<String, Object> modifyComebackDateConfirm(LeavePassDto leavePassDto, int pageNum, int amount) {
        log.info("modifyComebackDateConfirm()");
        leavePassMapper.updateApproveLeavePass(leavePassDto.getNo());
        return commonClass(leavePassDto.getNo(), leavePassDto.getSchool_no(), pageNum, amount);
    }

    private Map<String, Object> commonClass(int no, String schoolNo, int pageNum, int amount){
        Map<String, Object> map = new HashMap<>();
        Criteria criteria = new Criteria(pageNum, amount);
        int total = leavePassMapper.selectLeavePasses(schoolNo);
        PageMakerDto pageMakerDto = new PageMakerDto(schoolNo,criteria, total);
        map.put("pageMakerDto", pageMakerDto);
        List<LeavePassDto> leavePassDtos = leavePassMapper.selectLeavePassList(pageMakerDto);
        map.put("leavePassDtos", leavePassDtos);

        return map;
    }
}