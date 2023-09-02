package com.oneteam.dormeaseadmin.api.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsResponseDTO {
    String requestId;
    LocalDateTime requestTime;
    String statusCode;
    String statusName;
}
