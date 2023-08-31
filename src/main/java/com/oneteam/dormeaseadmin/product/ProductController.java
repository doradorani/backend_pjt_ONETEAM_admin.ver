package com.oneteam.dormeaseadmin.product;

import com.oneteam.dormeaseadmin.admin.member.AdminDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /*
     * 상품 페이지
     */
    @GetMapping({"", "/"})
    public String productHome(){
        log.info("productHome()");

        String nextPage = "product/productHome";

        return nextPage;
    }

    /*
     * 상품 등록 페이지
     */
    @GetMapping("/registProductForm")
    public String registProductForm(Model model){
        log.info("registProductForm()");

        String nextPage = "product/registProductForm";

        AdminDto loginedAdminDto = new AdminDto();
        loginedAdminDto.setSchool_no("7004207");
        loginedAdminDto.setId("test1");
        loginedAdminDto.setName("test1");
        loginedAdminDto.setGrade("admin_001");

        model.addAttribute("loginedAdminDto", loginedAdminDto);

        return nextPage;
    }

    /*
     * 상품 등록 페이지
     */
    @PostMapping("/registProductConfirm")
    public String registProductConfirm(ProductHistoryDto productHistoryDto){
        log.info("registProductConfirm()");

        int result = productService.registProductConfirm(productHistoryDto);

        return "redirect:/product";
    }

    /*
     * 전체 상품 조회(ajax)
     */
    @PostMapping("/selectAllProduct")
    @ResponseBody
    public Object selectAllProduct(){
        log.info("selectAllProduct()");

        Map<String, Object> resultMap = productService.selectAllProduct();

        return resultMap;
    }

    /*
     * 상품 검색 (ajax)
     */
    @PostMapping("/selectProduct")
    @ResponseBody
    public Object selectProduct(@RequestBody Map<String ,String> msgMap){
        log.info("selectProduct()");

        String productName = msgMap.get("name");

        Map<String, Object> resultMap = productService.selectProduct(productName);

        return resultMap;
    }

}
