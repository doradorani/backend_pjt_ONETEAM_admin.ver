package com.oneteam.dormeaseadmin.product;

import com.oneteam.dormeaseadmin.admin.member.MemberDto;
import com.oneteam.dormeaseadmin.utils.pagination.PageDefine;
import com.oneteam.dormeaseadmin.utils.pagination.PageMakerDto;
import com.oneteam.dormeaseadmin.utils.ProductUploadFileService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/product")
public class ProductController {

    //생성자 주입
    private final ProductService productService;
    private final ProductUploadFileService productUploadFileService;

    public ProductController(ProductService productService, ProductUploadFileService productUploadFileService) {
        this.productService = productService;
        this.productUploadFileService = productUploadFileService;
    }

    /*
     * 상품 페이지
     */
    @GetMapping({"", "/"})
    public String productHome() {
        log.info("productHome()");

        String nextPage = "product/productHome";
        return nextPage;
    }


    /*
     * 상품 공지 (전체 관리자용)
     */
    @GetMapping("/productNotice")
    public String productNotice(HttpSession session,
                                Model model,
                                @RequestParam(value = "keyWord", required = false, defaultValue = "") String keyWord,
                                @RequestParam(value = "pageNum", required = false, defaultValue = PageDefine.DEFAULT_PRODUCT_PAGE_NUMBER) int pageNum,
                                @RequestParam(value = "amount", required = false, defaultValue = PageDefine.DEFAULT_PRODUCT_AMOUNT) int amount) {
        log.info("productNotice()");
        String nextPage = "product/productNotice";

        MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");

        //페이지와 DTO 동시 관리
        Map<String, Object> listPage = productService.productNoticeList(keyWord, pageNum, amount);

        List<ProductNoticeDto> productNoticeDtos = (List<ProductNoticeDto>) listPage.get("productNoticeDtos");
        PageMakerDto pageMakerDto = (PageMakerDto) listPage.get("pageMakerDto");

        System.out.println("productNoticeDtos ==> " + productNoticeDtos);
        System.out.println("pageMakerDto ==> " + pageMakerDto);

        model.addAttribute("productNoticeDtos", productNoticeDtos);
        model.addAttribute("pageMakerDto", pageMakerDto);
        model.addAttribute("keyWord", keyWord);

        return nextPage;
    }

    /*
     * 상품 등록 페이지 (학교 관리자 용)
     */
    @GetMapping("/registProductForm")
    public String registProductForm(Model model, HttpSession session) {
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
     * 상품 등록 확인 (학교 관리자 용)
     */
    @PostMapping("/registProductConfirm")
    public String registProductConfirm(ProductRegistDto productRegistDto,
                                       @RequestParam("img") List<String> img,
                                       @RequestParam("name") List<String> name,
                                       @RequestParam("price") List<Integer> price) {
        log.info("registProductConfirm()");

        int result = productService.registProductConfirm(productRegistDto, img, name, price);

        return "redirect:/product";
    }

    /*
     * 전체 상품 조회(ajax) (학교 관리자 용)
     */
    @PostMapping("/selectAllProduct")
    @ResponseBody
    public Object selectAllProduct() {
        log.info("selectAllProduct()");

        Map<String, Object> resultMap = productService.selectAllProduct();

        return resultMap;
    }

    /*
     * 상품 검색 (ajax) (학교 관리자 용)
     */
    @PostMapping("/selectProduct")
    @ResponseBody
    public Object selectProduct(@RequestBody Map<String, String> msgMap) {
        log.info("selectProduct()");

        String productName = msgMap.get("name");

        Map<String, Object> resultMap = productService.selectProduct(productName);

        return resultMap;
    }

    /*
     * 이미 등록 상품 여부 (ajax) (학교 관리자 용)
     */
    @PostMapping("/isExistDatabase")
    @ResponseBody
    public Object isExistDatabase(@RequestBody Map<String, String> msgMap, HttpSession session) {
        log.info("isExistDatabase()");

        String productName = msgMap.get("name");

        Map<String, Object> resultMap = productService.isExistDatabase(productName,
                (MemberDto) session.getAttribute("loginedMemberDto"));

        return resultMap;
    }

    /*
     * 등록 상품 리스트 (학교 관리자 용)
     */
    @GetMapping("/registProductList")
    public String registProductList(HttpSession session,
                                    Model model,
                                    @RequestParam(value = "keyWord", required = false, defaultValue = "") String keyWord,
                                    @RequestParam(value = "pageNum", required = false, defaultValue = PageDefine.DEFAULT_PRODUCT_PAGE_NUMBER) int pageNum,
                                    @RequestParam(value = "amount", required = false, defaultValue = PageDefine.DEFAULT_PRODUCT_AMOUNT) int amount) {
        log.info("registProductList()");
        String nextPage = "product/registProductList";

        MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");

        //페이지와 DTO 동시 관리
        Map<String, Object> listPage = productService.registProductList("7004207", keyWord, pageNum, amount); //loginedMemberDto.getSchool_no()
        List<ProductRegistDto> productRegistDtos = (List<ProductRegistDto>) listPage.get("productRegistDtos");
        PageMakerDto pageMakerDto = (PageMakerDto) listPage.get("pageMakerDto");
        //pageMakerDto=PageMakerDto{startPage=1, endPage=2, prev=false, next=false, total=5, totalPage=2, criteria=Criteria(pageNum=1, amount=3, skip=0)

        model.addAttribute("productRegistDtos", productRegistDtos);
        model.addAttribute("pageMakerDto", pageMakerDto);
        model.addAttribute("keyWord", keyWord);

        return nextPage;

    }


    /*
     * 등록 상품 리스트 삭제 (학교 관리자 용)
     */
    @GetMapping("/unRegistProduct")
    public String unRegistProduct(HttpSession session, @RequestParam("no") int no) {
        log.info("unRegistProduct()");
        String nextPage = "redirect:/product/registProductList";

        int result = productService.unRegistProduct(no);

        return nextPage;
    }

    /*
     * 상품 등록 페이지 (최종 관리자 용)
     */
    @GetMapping("/adminProductForm")
    public String adminProductForm(HttpSession session, Model model) {
        log.info("adminProductForm()");
        String nextPage = "product/adminProductForm";

        MemberDto loginedMemberDto = new MemberDto();
        loginedMemberDto.setId("admin");
        loginedMemberDto.setName("admin");
        session.setAttribute("loginedMemberDto", loginedMemberDto);
        session.setMaxInactiveInterval(60 * 30);
        model.addAttribute("loginedMemberDto", loginedMemberDto);

        return nextPage;
    }


    /*
     * 상품 등록 중복 확인 (최종 관리자 용)
     */
    @PostMapping("/adminAlreadyRegist")
    @ResponseBody
    public Object adminAlreadyRegist(@RequestBody Map<String, String> msgMap) {
        log.info("adminAlreadyRegist()");

        String productName = msgMap.get("name");
        Map<String, Object> resultMap = productService.adminAlreadyRegist(productName);

        return resultMap;
    }

    /*
     * 상품 등록 확인 (최종 관리자 용)
     */
    @PostMapping("/adminProductConfirm")
    public String adminProductConfirm(HttpSession session, ProductDto productDto,
                                      @RequestParam("file") MultipartFile file) {
        log.info("adminProductConfirm()");

        MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");

        String savedFileName = "";
        if (!file.isEmpty()) {          //파일 이미지를 업로드 하였다면
            savedFileName = productUploadFileService.upload(file);
        }
        productDto.setImg(savedFileName);

        int result = productService.adminProductConfirm(productDto);
        int notice = 0;

        if (result > 0) { // 등록 or 해제에 성공하였다면 공지에 개시
            notice = productService.productNotice(productDto, 1);
        }

        return "redirect:/product";
    }

    /*
     * 등록 상품 리스트 (최종 관리자 용)
     */
    @GetMapping("/adminProductList")
    public String adminProductList(HttpSession session,
                                   Model model,
                                   @RequestParam(value = "keyWord", required = false, defaultValue = "") String keyWord,
                                   @RequestParam(value = "pageNum", required = false, defaultValue = PageDefine.DEFAULT_PRODUCT_PAGE_NUMBER) int pageNum,
                                   @RequestParam(value = "amount", required = false, defaultValue = PageDefine.DEFAULT_PRODUCT_AMOUNT) int amount) {
        log.info("adminProductList()");
        String nextPage = "product/adminProductList";

        MemberDto loginedMemberDto = (MemberDto) session.getAttribute("loginedMemberDto");

        //페이지와 DTO 동시 관리
        Map<String, Object> listPage = productService.adminProductList(keyWord, pageNum, amount);

        List<ProductDto> productDtos = (List<ProductDto>) listPage.get("productDtos");
        PageMakerDto pageMakerDto = (PageMakerDto) listPage.get("pageMakerDto");
        //pageMakerDto=PageMakerDto{startPage=1, endPage=2, prev=false, next=false, total=5, totalPage=2, criteria=Criteria(pageNum=1, amount=3, skip=0)

        System.out.println("productDtos ==> " + productDtos);

        model.addAttribute("productDtos", productDtos);
        model.addAttribute("pageMakerDto", pageMakerDto);
        model.addAttribute("keyWord", keyWord);

        return nextPage;
    }

    /*
     * 등록 상품 리스트 삭제 (최종 관리자 용)
     */
    @GetMapping("/unRegistProductAdmin")
    public String unRegistProductAdmin(HttpSession session, @RequestParam("no") int no) {
        log.info("unRegistProductAdmin()");
        String nextPage = "redirect:/product/adminProductList";

        int result = productService.unRegistProductAdmin(no);
        int notice = 0;

        if (result > 0) { // 등록 or 해제에 성공하였다면 공지에 개시
            ProductDto productDto = new ProductDto();
            productDto.setNo(no);
            notice = productService.productNotice(productDto, 0);
        }

        return nextPage;
    }

}
