package com.oneteam.dormeaseadmin.admin;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AdminService {

    @Autowired
    IAdminMapper iAdminMapper;

    //관리자 계정생성 확인
    public int createAccountConfirm(AdminDto adminDto) {
        log.info("createAccountConfirm()");

        return iAdminMapper.createAccountConfirm(adminDto);
    }
}
