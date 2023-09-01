package com.oneteam.dormeaseadmin.product;

import com.oneteam.dormeaseadmin.admin.member.AdminDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class ProductService {

    private final IProductMapper productMapper;
    public ProductService(IProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    // 상품 등록
    public int registProductConfirm(ProductHistoryDto productHistoryDto,
                                    List<String> name,
                                    List<Integer> price) {
        log.info("registProductConfirm()");


        List<ProductHistoryDto> productHistoryDtos = new ArrayList<>();
        ProductHistoryDto addData = productMapper.selectSchoolZipCodeAndName(productHistoryDto);

        for(int i=0; i<name.size(); i++){
            ProductHistoryDto phDto = new ProductHistoryDto();
            phDto.setZip_code(addData.getZip_code());
            phDto.setSchool_name(addData.getSchool_name());
            phDto.setAdmin_id((productHistoryDto.getAdmin_id()));
            phDto.setAdmin_name(productHistoryDto.getAdmin_name());
            phDto.setProduct_name(name.get(i));
            phDto.setProduct_price(price.get(i));

            productHistoryDtos.add(phDto);
        }
        return productMapper.registProductConfirm(productHistoryDtos);
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

    //상품 검색
    public Map<String, Object> isExistDatabase(String productName, AdminDto loginedAdminDto) {
        log.info("isExistDatabase()");

        Map<String, Object> map = new HashMap<>();
        ProductHistoryDto productHistoryDto = new ProductHistoryDto();
        productHistoryDto.setProduct_name(productName);
        productHistoryDto.setZip_code(productMapper.selectSchoolZipCode(loginedAdminDto));

        int countExist = productMapper.isExistDatabase(productHistoryDto);
        map.put("countExist", countExist);
        return map;
    }
}
