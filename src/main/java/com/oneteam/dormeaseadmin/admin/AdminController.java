package com.oneteam.dormeaseadmin.admin;

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

        String nextPage = "home";

        log.info(adminDto);

        int result = adminService.createAccountConfirm(adminDto);

        log.info("result => " + result);

        return nextPage;
    }
}
