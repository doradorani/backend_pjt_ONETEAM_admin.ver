package com.oneteam.dormeaseadmin.admin.leavePass;

import com.oneteam.dormeaseadmin.admin.member.MemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/admin/leavePass")
public class LeavePassController {

    private final LeavePassService leavePassService;

    public LeavePassController(LeavePassService leavePassService) {
        this.leavePassService = leavePassService;
    }

    @GetMapping("/leavePassList")
    public String leaveOutList(Model model, HttpSession session){
        log.info("leaveOutList()");
        String nextPage = "admin/leaveOutList";
        MemberDto loginedAdminDto = (MemberDto) session.getAttribute("loginedAdminDto");
        List<LeavePassDto> leavePassDtos = leavePassService.leaveOutList(loginedAdminDto.getSchool_no());
        model.addAttribute("leavePassDtos", leavePassDtos);

        return nextPage;
    }
    @GetMapping("/approveLeavePass")
    @ResponseBody
    public Object approveLeavePass(HttpSession session, @RequestParam int no){
        log.info("approveLeavePass()");
        MemberDto loginedAdminDto = (MemberDto) session.getAttribute("loginedAdminDto");
        Map<String, Object> map = new HashMap<>();
        List<LeavePassDto> leavePassDtos = leavePassService.approveLeavePass(no, loginedAdminDto.getSchool_no());
        map.put("leavePassDtos", leavePassDtos);

        return map;
    }
    @GetMapping("/modifyLeavePass")
    public Object modifyLeavePass(@RequestParam int no){
        log.info("modifyLeavePass()");

        return null;
    }
}
