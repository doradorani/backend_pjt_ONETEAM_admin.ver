package com.oneteam.dormeaseadmin.product;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class ProductService {

    @Autowired
    IProductMapper productMapper;

    // 상품 등록
    public int registProductConfirm(ProductHistoryDto productHistoryDto) {
        log.info("registProductConfirm()");

        ProductHistoryDto addData = productMapper.selectSchoolZipCodeAndName(productHistoryDto);

        productHistoryDto.setZip_code(addData.getZip_code());
        productHistoryDto.setSchool_name(addData.getSchool_name());

        return productMapper.registProductConfirm(productHistoryDto);
    }

    // 상품 전체 조회
    public Map<String, Object> selectAllProduct() {
        log.info("selectAllProduct()");

        Map<String, Object> map = new HashMap<>();
        List<ProductDto> productDtos = productMapper.selectAllProduct();

        map.put("productDtos", productDtos);

        return map;
    }

    //상품 검색
    public Map<String, Object> selectProduct(String productName) {
        log.info("selectProduct()");

        Map<String, Object> map = new HashMap<>();
        List<ProductDto> productDtos = productMapper.selectProduct(productName);

        map.put("productDtos", productDtos);

        return map;
    }


}
