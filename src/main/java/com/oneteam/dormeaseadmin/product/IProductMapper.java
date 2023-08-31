package com.oneteam.dormeaseadmin.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IProductMapper {

    public ProductHistoryDto selectSchoolZipCodeAndName(ProductHistoryDto productHistoryDto);
    public int registProductConfirm(ProductHistoryDto productHistoryDto);
    public List<ProductDto> selectAllProduct();
    public List<ProductDto> selectProduct(String productName);
}
