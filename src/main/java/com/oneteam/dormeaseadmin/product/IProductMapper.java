package com.oneteam.dormeaseadmin.product;

import com.oneteam.dormeaseadmin.admin.member.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IProductMapper {

    public String selectSchoolZipCode(MemberDto loginedMemberDto);
    public ProductHistoryDto selectSchoolZipCodeAndName(ProductHistoryDto productHistoryDto);
    public int registProductConfirm(List<ProductHistoryDto> productHistoryDtos);
    public List<ProductDto> selectAllProduct();
    public List<ProductDto> selectProduct(String productName);
    public List<ProductDto> selectProductList(List<String> names);
    public int isExistDatabase(ProductHistoryDto productHistoryDto);
}
