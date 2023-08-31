package com.oneteam.dormeaseadmin.admin.member;

import lombok.Data;

@Data
public class AdminDto {
    private int no;                 // COMMENT '관리자 번호'
    private int school_no;          // COMMENT '학교 번호'
    private String id;              // COMMENT '관리자 아이디'
    private String password;        // COMMENT '관리자 비밀번호'
    private String name;            // COMMENT '관리자 이름'
    private int gender;             // COMMENT '관리자 성별'
    private String grade;           // COMMENT '관리자 등급'
    private String position;        // COMMENT '관리자 부서'
    private String phone;           // COMMENT '관리자 전화 번호'
    private String zip_code;        // COMMENT '관리자 우편번호'
    private String address;         // COMMENT '관리자 주소'
    private String address_detail1; // COMMENT '관리자 상세 주소1'
    private String address_detail2; // COMMENT '관리자 상세 주소2'
    private String mail;            // COMMENT '관리자 메일'
    private String reg_date;        // COMMENT '관리자 등 날짜'
    private String mod_date;        // COMMENT '관리자 수정 날짜'
}
