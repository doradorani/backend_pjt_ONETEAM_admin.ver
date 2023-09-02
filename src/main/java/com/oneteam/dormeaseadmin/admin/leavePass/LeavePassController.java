package com.oneteam.dormeaseadmin.admin.leavePass;

import com.oneteam.dormeaseadmin.admin.member.MemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/leavePass")
public class LeavePassController {

    private final LeavePassService leavePassService;

    public LeavePassController(LeavePassService leavePassService) {
        this.leavePassService = leavePassService;
    }

    @GetMapping("/leavePassList")
    public String leaveOutList(Model model, HttpSession session){
        log.info("leaveOutList()");
        String nextPage = "admin/leaveOutList";
        MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");
        List<LeavePassDto> leavePassDtos = leavePassService.leaveOutList(loginedMemberDto.getSchool_no());
        model.addAttribute("leavePassDtos", leavePassDtos);

        return nextPage;
    }
    @GetMapping("/approveLeavePass")
    @ResponseBody
    public Object approveLeavePass(HttpSession session, @RequestParam int no){
        log.info("approveLeavePass()");
        MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");
        Map<String, Object> map = new HashMap<>();
        List<LeavePassDto> leavePassDtos = leavePassService.approveLeavePass(no, loginedMemberDto.getSchool_no());
        map.put("leavePassDtos", leavePassDtos);

        return map;
    }

    @GetMapping("/modifyLeavePassForm")
    public String modifyLeavePassForm(@RequestParam int no, Model model){
        log.info("modifyLeavePassForm()");
        String nextPage = "leavePass/modifyLeavePassForm";
        LeavePassDto leavePassDto = leavePassService.modifyLeavePassForm(no);
        model.addAttribute("leavePassDto", leavePassDto);

        return nextPage;
    }
    @PostMapping("/modifyLeavePassConfirm")
    public String modifyLeavePassConfirm(LeavePassDto leavePassDto){
        log.info("modifyLeavePassConfirm()");
        String nextPage = "redirect:/leavePass/leaveOutList";
        int result = leavePassService.modifyLeavePassConfirm(leavePassDto);

        return nextPage;
    }
}
