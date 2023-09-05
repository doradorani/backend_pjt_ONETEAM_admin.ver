package com.oneteam.dormeaseadmin.admin.member;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class MemberService {

    private final IMemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberService(IMemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    //관리자 계정생성 중복 확인
    public Map<String, Object> idDuplicationCheck(String id) {
        log.info("loginAccountConfirm()");

        boolean isDuplicateID = memberMapper.selectDuplicateByID(id);

        Map<String, Object> map = new HashMap<>();
        map.put("isDuplicateID", isDuplicateID);
        return map;
    }

    //관리자 계정생성 확인
    public int createAccountConfirm(MemberDto memberDto) {
        log.info("createAccountConfirm()");

        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        return memberMapper.createAccountConfirm(memberDto);
    }

    //관리자 로그인 확인
    public Map<String, Object> loginConfirm(MemberDto memberDto) {
        log.info("loginConfirm()");
        MemberDto loginedMemberDto = memberMapper.selectMemberByID(memberDto.getId());
        Map<String, Object> map = new HashMap<>();
        if (loginedMemberDto != null) {
            if (!passwordEncoder.matches(memberDto.getPassword(), loginedMemberDto.getPassword())) {
                memberMapper.updateFailCount(memberDto);
                map.put("fail_count", loginedMemberDto.getFail_count() + 1);
                map.put("result", false);
                loginedMemberDto = null;
            } else {
                if (loginedMemberDto.getFail_count() >= 5) {
                    map.put("fail_count", loginedMemberDto.getFail_count());
                    loginedMemberDto = null;
                    map.put("result", false);
                } else {
                    loginedMemberDto.setPassword(null);
                    memberMapper.updateFailCount(loginedMemberDto);
                    memberMapper.insertAdminLoginHistory(loginedMemberDto);
                    loginedMemberDto.setFail_count(0);
                    map.put("fail_count", 0);
                    map.put("result", true);
                }
            }
        }
        map.put("loginedMemberDto", loginedMemberDto);
        return map;
    }

    public void logoutConfirm(MemberDto loginedMemberDto) {
        log.info("loginConfirm()");

        int maxNo = memberMapper.selectMaxNoFromLoginHistory(loginedMemberDto.getId());
        log.info("maxNo{}", maxNo);
        memberMapper.updateAdminLoginHistory(maxNo);
    }
}
