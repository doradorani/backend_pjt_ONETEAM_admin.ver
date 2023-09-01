package com.oneteam.dormeaseadmin.admin.member;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class MemberService {

    private final IMemberMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberService(IMemberMapper adminMapper, PasswordEncoder passwordEncoder) {
        this.adminMapper = adminMapper;
        this.passwordEncoder = passwordEncoder;
    }

    //관리자 계정생성 중복 확인
    public Map<String, Object> idDuplicationCheck(String id) {
        log.info("loginAccountConfirm()");

        boolean isDuplicateID = adminMapper.selectDuplicateByID(id);

        Map<String, Object> map = new HashMap<>();
        map.put("isDuplicateID", isDuplicateID);
        return map;
    }

    //관리자 계정생성 확인
    public int createAccountConfirm(MemberDto adminDto) {
        log.info("createAccountConfirm()");

        adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        return adminMapper.createAccountConfirm(adminDto);
    }

    //관리자 로그인 확인
    public MemberDto loginAccountConfirm(MemberDto adminDto) {
        log.info("loginAccountConfirm()");

        MemberDto loginedAdminDto = adminMapper.loginAccountConfirm(adminDto.getId());

        if(loginedAdminDto != null){
            if (!passwordEncoder.matches(adminDto.getPassword(), loginedAdminDto.getPassword())) {
                loginedAdminDto = null;
            }
        }
        return loginedAdminDto;
    }

}
