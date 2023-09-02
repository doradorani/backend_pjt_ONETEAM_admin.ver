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
    public int createAccountConfirm(MemberDto memberDto) {
        log.info("createAccountConfirm()");

        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        return adminMapper.createAccountConfirm(memberDto);
    }

    //관리자 로그인 확인
    public MemberDto loginConfirm(MemberDto memberDto) {
        log.info("loginConfirm()");

        MemberDto loginedMemberDto = adminMapper.loginConfirm(memberDto.getId());

        if(loginedMemberDto != null){
            if (!passwordEncoder.matches(memberDto.getPassword(), loginedMemberDto.getPassword())) {
                loginedMemberDto = null;
            }
        }
        return loginedMemberDto;
    }

}
