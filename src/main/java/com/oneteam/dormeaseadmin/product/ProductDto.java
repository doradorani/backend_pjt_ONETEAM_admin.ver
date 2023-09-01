package com.oneteam.dormeaseadmin.product;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.Data;

@Data
public class ProductDto {

    private @SQLInjectionSafe String name;        // COMMENT '상품 명'
    private @SQLInjectionSafe int price;          // COMMENT '상품 가격'
}
