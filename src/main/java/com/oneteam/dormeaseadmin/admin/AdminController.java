package com.oneteam.dormeaseadmin.admin;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

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
     * 관리자 계정생성 확인
     */
    @PostMapping("/createAccountConfirm")
    public String createAccountConfirm(AdminDto adminDto){
        log.info("createAccountConfirm()");

        String nextPage = "redirect:/";

        int result = adminService.createAccountConfirm(adminDto);

        return nextPage;
    }

    /*
     * 관리자 로그인 폼
     */
    @GetMapping("/loginAccountForm")
    public String loginAccountForm(){
        log.info("loginAccountForm()");

        String nextPage = "admin/loginAccountForm";

        return nextPage;
    }

    /*
     * 관리자 로그인 확인
     */
    @PostMapping("/loginAccountConfirm")
    public String loginAccountConfirm(AdminDto adminDto, HttpSession session){
        log.info("loginAccountConfirm()");

        String nextPage = "redirect:/";

        AdminDto loginedAdminDto = adminService.loginAccountConfirm(adminDto);

        if(loginedAdminDto != null){
            session.setAttribute("loginedAdminDto", loginedAdminDto);
            session.setMaxInactiveInterval(30*60);
        }

        return nextPage;
    }





}
