package com.oneteam.dormeaseadmin.product;

import com.oneteam.dormeaseadmin.admin.member.MemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/product")
public class ProductController {

    //생성자 주입
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

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
    public String registProductForm(Model model, HttpSession session){
        log.info("registProductForm()");

        String nextPage = "product/registProductForm";

        MemberDto loginedMemberDto = new MemberDto();
        loginedMemberDto.setSchool_no("7004207");
        loginedMemberDto.setId("test1");
        loginedMemberDto.setName("test1");
        loginedMemberDto.setGrade("admin_001");

        session.setAttribute("loginedMemberDto", loginedMemberDto);
        session.setMaxInactiveInterval(60 * 30);
        model.addAttribute("loginedMemberDto", loginedMemberDto);

        return nextPage;
    }

    /*
     * 상품 등록 페이지
     */
    @PostMapping("/registProductConfirm")
    public String registProductConfirm(ProductHistoryDto productHistoryDto,
                                       @RequestParam("name") List<String> name,
                                       @RequestParam("price") List<Integer> price){
        log.info("registProductConfirm()");

        int result = productService.registProductConfirm(productHistoryDto, name, price);

        return "redirect:/product";
    }

    /*s
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

    /*
     * 이미 등록 상품 여부 (ajax)
     */
    @PostMapping("/isExistDatabase")
    @ResponseBody
    public Object isExistDatabase(@RequestBody Map<String ,String> msgMap, HttpSession session){
        log.info("isExistDatabase()");

        String productName = msgMap.get("name");

        Map<String, Object> resultMap = productService.isExistDatabase(productName,
                                                        (MemberDto) session.getAttribute("loginedMemberDto"));

        return resultMap;
    }

}
