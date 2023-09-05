package com.oneteam.dormeaseadmin.admin.member;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/admin/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /*
     * 관리자 계정생성 폼
     */
    @GetMapping("/createAccountForm")
    public String createAccountForm(){
        log.info("createAccountForm()");

        String nextPage = "admin/createAccountForm";

        return nextPage;
    }

    /*
     * 아이디 중복 여부 체크
     */

    @GetMapping("/idDuplicationCheck")
    @ResponseBody
    public Object idDuplicationCheck(@RequestParam String id) {
        log.info("idDuplicationCheck()");

        Map<String, Object> map = memberService.idDuplicationCheck(id);

        return map;
    }


    /*
     * 관리자 계정생성 확인
     */
    @PostMapping("/createAccountConfirm")
    public String createAccountConfirm(MemberDto adminDto){
        log.info("createAccountConfirm()");

        String nextPage = "redirect:/";

        int result = memberService.createAccountConfirm(adminDto);

        return nextPage;
    }

    /*
     * 관리자 로그인 폼
     */
    @GetMapping("/loginForm")
    public String loginForm(){
        log.info("loginForm()");

        String nextPage = "admin/loginForm";

        return nextPage;
    }

    /*
     * 관리자 로그인 확인
     */

    @PostMapping("/loginConfirm")
    @ResponseBody
    public Object loginConfirm(MemberDto memberDto, HttpSession session) {
        log.info("loginConfirm()");

        Map<String, Object> map = memberService.loginConfirm(memberDto);
        MemberDto loginedMemberDto = (MemberDto) map.get("loginedMemberDto");
        if(loginedMemberDto != null){
            session.setAttribute("loginedMemberDto", loginedMemberDto);
            session.setMaxInactiveInterval(30*60);
        }
        return map;

    }
   /*
     * 관리자 로그아웃 확인
     */
    @GetMapping("/logoutConfirm")
    public String logoutConfirm(HttpSession session){
        log.info("logoutConfirm()");

        MemberDto loginedMemberDto = (MemberDto)session.getAttribute("loginedMemberDto");
        memberService.logoutConfirm(loginedMemberDto);
        session.removeAttribute("loginedMemberDto");

        return "redirect:/";
    }
    /*
     * 학생 사용자 승인 리스트
     */
    @GetMapping("/studentApprovalList")
    public String studentApprovalList(HttpSession session){
        log.info("studentApprovalList()");

        return null;
    }
    /*
     * 학부모 사용자 승인 리스트
     */
    @GetMapping("/parentsApprovalList")
    public String parentsApprovalList(HttpSession session){
        log.info("parentsApprovalList()");


        return null;
    }


}
