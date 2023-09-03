package com.oneteam.dormeaseadmin.admin.leavePass.sms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.oneteam.dormeaseadmin.admin.member.MemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/sms")
public class SmsController {

    private final SmsService smsService;

    public SmsController(SmsService smsService){
        this.smsService = smsService;
    }

    @PostMapping("/sendMessages")
    @ResponseBody
    public Object sendMessages(SmsDTO smsDTO) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        log.info("sendMessages()");

        SmsResponseDTO response = smsService.sendMessages(smsDTO);

        Map<String, Object> map = new HashMap<>();

        map.put("response", response);

        return map;
    }
    @GetMapping("/allSendMessages")
    @ResponseBody
    public Object allSendMessages(HttpSession session) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        log.info("sendMessages()");

        MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");

        return smsService.allSendMessages(loginedMemberDto.getSchool_no());
    }

}
