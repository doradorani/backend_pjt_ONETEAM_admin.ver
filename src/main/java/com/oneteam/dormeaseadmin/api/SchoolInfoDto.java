package com.oneteam.dormeaseadmin.api;

import lombok.Data;

@Data
public class SchoolInfoDto {
    private String atpt_code;           //시도교육청코드
    private String atpt_name;           //시도교육청명
    private String zip_code;            //학교우편번호
    private String school_code;         //표준학교코드
    private String school_name;         //학교 명
    private String school_knd;          //학교 종류
    private int status;              //학교 상태
}
