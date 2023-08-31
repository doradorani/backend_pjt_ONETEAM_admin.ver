package com.oneteam.dormeaseadmin.admin.member;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /*
     * 관리자 계정생성 폼
     */
    @GetMapping("/member/createAccountForm")
    public String createAccountForm(){
        log.info("createAccountForm()");

        String nextPage = "admin/createAccountForm";

        return nextPage;
    }

    /*
     * 아이디 중복 여부 체크
     */

    @GetMapping("/member/idDuplicationCheck")
    @ResponseBody
    public Object idDuplicationCheck(@RequestParam String id) {
        log.info("idDuplicationCheck()");

        Map<String, Object> map = adminService.idDuplicationCheck(id);

        return map;

    }


    /*
     * 관리자 계정생성 확인
     */
    @PostMapping("/member/createAccountConfirm")
    public String createAccountConfirm(AdminDto adminDto){
        log.info("createAccountConfirm()");

        String nextPage = "redirect:/";

        int result = adminService.createAccountConfirm(adminDto);

        return nextPage;
    }

    /*
     * 관리자 로그인 폼
     */
    @GetMapping("/member/loginForm")
    public String loginForm(){
        log.info("loginForm()");

        String nextPage = "admin/loginForm";

        return nextPage;
    }

    /*
     * 관리자 로그인 확인
     */
    @PostMapping("/member/loginConfirm")
    @ResponseBody
    public Object loginConfirm(AdminDto adminDto, HttpSession session){
        log.info("loginConfirm()");

        AdminDto loginedAdminDto = adminService.loginAccountConfirm(adminDto);

        Map<String, Object> map = new HashMap<>();

        if(loginedAdminDto != null){
            session.setAttribute("loginedAdminDto", loginedAdminDto);
            session.setMaxInactiveInterval(30*60);

            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }

        return map;
    }







}
