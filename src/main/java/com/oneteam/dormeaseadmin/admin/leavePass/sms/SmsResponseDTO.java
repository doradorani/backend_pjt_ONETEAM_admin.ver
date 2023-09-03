package com.oneteam.dormeaseadmin.admin.leavePass.sms;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
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
    private @SQLInjectionSafe String requestId;
    private @SQLInjectionSafe LocalDateTime requestTime;
    private @SQLInjectionSafe String statusCode;
    private @SQLInjectionSafe String statusName;
}
