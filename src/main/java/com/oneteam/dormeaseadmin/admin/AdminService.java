package com.oneteam.dormeaseadmin.admin;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AdminService {

    @Autowired
    IAdminMapper adminMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    //관리자 계정생성 중복 확인
    public boolean isAdmin(String id) {
        log.info("isAdmin()");

        return adminMapper.isAdmin(id) > 0 ? true : false;
    }

    //관리자 계정생성 확인
    public int createAccountConfirm(AdminDto adminDto) {
        log.info("createAccountConfirm()");

        if(isAdmin(adminDto.getId())){
            log.info("Admin ID is already exist");
            return 0;
        }

        adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));

        return adminMapper.createAccountConfirm(adminDto);
    }

    //관리자 로그인 확인
    public AdminDto loginAccountConfirm(AdminDto adminDto) {
        log.info("loginAccountConfirm()");

        AdminDto loginedAdminDto = adminMapper.loginAccountConfirm(adminDto.getId());

        if(loginedAdminDto != null){
            if (!passwordEncoder.matches(adminDto.getPassword(), loginedAdminDto.getPassword())) {
                loginedAdminDto = null;
            }
        }

        return loginedAdminDto;
    }
}
