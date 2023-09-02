package com.oneteam.dormeaseadmin.api.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsRequestDTO {
    String type;
    String contentType;
    String countryCode;
    String from;
    String content;
    List<SmsDTO> messages;
}
