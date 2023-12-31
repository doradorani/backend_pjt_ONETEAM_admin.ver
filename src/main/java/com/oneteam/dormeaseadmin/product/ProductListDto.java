package com.oneteam.dormeaseadmin.product;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //모든 인자 값을 받는 생성자
public class ProductListDto {
    
    private @SQLInjectionSafe String zip_code;        // 학교 우편 번호
    private @SQLInjectionSafe String name;            // 상품 이름
    private @SQLInjectionSafe int skip;               // 페이지 넘버에 따른 스킵 개수
    private @SQLInjectionSafe int amount;             // 한 페이지에 보여줄 개수
    private @SQLInjectionSafe String keyWord;         // 키워드

    public ProductListDto(int skip, int amount) {
        this.skip = skip;
        this.amount = amount;
    }

    public ProductListDto(String zip_code, int skip, int amount) {
        this.zip_code = zip_code;
        this.skip = skip;
        this.amount = amount;
    }

}
