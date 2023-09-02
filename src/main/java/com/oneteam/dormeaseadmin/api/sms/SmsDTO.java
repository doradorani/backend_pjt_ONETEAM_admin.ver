package com.oneteam.dormeaseadmin.api.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsDTO {
    String to;
    String content;
}
