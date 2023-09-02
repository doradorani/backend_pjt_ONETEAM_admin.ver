package com.oneteam.dormeaseadmin.api.sms;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
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

}
