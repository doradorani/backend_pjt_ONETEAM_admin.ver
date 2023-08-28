package com.oneteam.dormeaseadmin.admin.history;

import lombok.Data;

@Data
public class AdminHistoryDto {
    private int no;                     //COMMENT '관리자 히스토리 번호'
    private String admin_id;            //COMMENT '접속한 관리자 아이디'
    private String admin_name;          //COMMENT '접속한 관리자 이름'
    private String admin_grade;         //COMMENT '접속한 관리자 등급'
    private String admin_mail;          //COMMENT '접속한 관리자 이름'
    private String login_ip;            //COMMENT '접속한 관리자 아이피'
    private String login_date;          //COMMENT '접속 날짜'
    private String logout_date;         //COMMENT '접속 시간'
}
