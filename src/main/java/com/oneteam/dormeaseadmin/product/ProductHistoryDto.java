package com.oneteam.dormeaseadmin.product;

import lombok.Data;

@Data
public class ProductHistoryDto {
    private int no;                     // COMMENT '상품 등록 번호'
    private String product_name;        // COMMENT '상품 이름'
    private int product_price;          // COMMENT '상품 가격'
    private String zip_code;            // COMMENT '등록한 학교 우편 번호'
    private String school_name;         // COMMENT '등록한 학교 명'
    private String admin_id;            // COMMENT '등록한 관리자 아이디'
    private String admin_name;          // COMMENT '등록한 관리자 이름'
    private int status;                 // COMMENT '상품 등록 상태'
    private String reg_date;            // COMMENT '상품 등록 날짜'
    private String mod_date;            // COMMENT '상품 수정 날짜'

    //데이터 조인을 위한 추가 데이터
    private String school_no;           // COMMENT '등록한 학교 고유 번호'
    private String admin_grade;         // COMMENT '등록한 관리자 등급'
}
