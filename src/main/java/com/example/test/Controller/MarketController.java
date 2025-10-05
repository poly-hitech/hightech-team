package com.example.test.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.Model.Market;
import com.example.test.Model.OrdersDetails;
import com.example.test.Model.Ranking;
import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceFile;
import com.example.test.Model.ResourceShop;
import com.example.test.Model.ResourceSubCategory;
import com.example.test.Model.ShopReview;
import com.example.test.Model.Users;
import com.example.test.Service.OrdersService;
import com.example.test.Service.ResourceShopService;
import com.example.test.Service.ShopReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/shop")
public class MarketController {
    private final static String path = "shop/";

    @Autowired
    ResourceShopService resourceShopService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    ShopReviewService shopReviewService;

    // --------------------------------조회---------------------------------------------
    // 전체 상점 목록
    @GetMapping("public/list")
    String shopList(Model model, HttpSession session) {
        List<ResourceCategory> list = resourceShopService.list();

        log.info("1차 카테고리 객체 확인: {}" + list);

        // 넘어오는 값을 확인하기 위한 코드
        for (int a = 0; a < list.size(); a++) {
            ResourceCategory rc = list.get(a);
            log.info("리소스 사이즈 : {}" + list.size() + "리소스 1차 번호: {}" + rc.getResourceCategoryId());
            // 서브 카테고리 리스트를 호출
            List<ResourceSubCategory> sc = rc.getResourceSubCategory();
            // 호출한 서브카테고리 리스트 만큼 반복시켜 서브 카테고리 각 객체 불러옴
            for (ResourceSubCategory rsc : sc) {
                Long id = rsc.getResourceSubCategoryId();
                String name = rsc.getResourceSubCategoryName();

                log.info("2차 카테고리 번호: {}" + id);
                log.info("2차 카테고리 이름: {}" + name);
                // 불러온 서브카테고리속에 속한 상점 정보 리스트를 불러옴
                List<ResourceShop> rsList = rsc.getResourceShop();
                // 불러온 상점정보리스트에 담긴 상점정보 단일 객체 호출
                for (ResourceShop resourceShop : rsList) {
                    Long shopId = resourceShop.getItemId();
                    String itemName = resourceShop.getItemName();
                    log.info("상점 아이템 번호: {}" + shopId);
                    log.info("상점 아이템 이름: {}" + itemName);
                    log.info("상점 아이템 배경이미지: {}", resourceShop.getResourceImage());

                    // 해당 상점정보의 랭킹 호출
                    Ranking rank = resourceShop.getRanking();
                    Long rankId = rank.getRankingId();
                    log.info("상점 아이템 랭킹번호: {}" + rankId);

                    // 해당 상품의 판매량 호출

                    // 상점 정보 속에 속한 파일 호출
                    List<ResourceFile> rfList = resourceShop.getResourceFile();
                    // 위와 동일한 방식
                    for (ResourceFile resourceFile : rfList) {
                        Long fileId = resourceFile.getItemId();
                        String fileName = resourceFile.getResourceFileName();
                        log.info("상점 파일경로: {}" + fileName);
                        // 가져온 파일명에서 uuid를 제외한 진짜 파일명을 불러오기위함.
                        if (fileName != null) {
                            int pos = fileName.lastIndexOf("_");

                            if (pos > 0) {
                                fileName = fileName.substring(pos + 1);
                                log.info("상점 파일 번호: {}" + fileId);
                                log.info("상점 파일명: {}" + fileName);
                                // 보여줄 파일명 설정
                            }
                        }
                    }
                }
            }
        }

        model.addAttribute("list", list);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String userAsString;
            userAsString = objectMapper.writeValueAsString(list);
            model.addAttribute("list2", userAsString);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return path + "list";
    }

    // 리소스 상품 상세
    @GetMapping("/detail/{itemId}")
    public String showDetail(@PathVariable Long itemId, HttpSession session, Model model)
            throws JsonProcessingException {
        int page = 1;
        int pageSize = 5;
        int startRow = 0;
        int endRow = page * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("itemId", itemId);
        params.put("sortType", "latest");
        params.put("startRow", startRow);
        params.put("endRow", endRow);

        ResourceShop resourceShop = resourceShopService.getItemById(itemId);
        List<ShopReview> reviewList = shopReviewService.getReviewsSorted(params);
        Integer totalCount = shopReviewService.getReviewCountByItemId(itemId);

        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        // 페이지 그룹 설정
        int pageGroupSize = 10;
        int startPage = 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("hasPrev", false);
        model.addAttribute("hasNext", endPage < totalPages);
        // 로그인한 유저의 유저 번호 호출
        Users member = (Users) session.getAttribute("member");

        if (member != null) {
            Long userId = member.getUserId();
            Map<String, Object> userParams = new HashMap<>();
            userParams.put("userId", userId);
            userParams.put("itemId", itemId);
            OrdersDetails ordersDetails = ordersService.getOrdersDetailsByUserIdAndItemId(userParams);
            model.addAttribute("ordersDetails", ordersDetails);
            model.addAttribute("ordersDetails2", new ObjectMapper().writeValueAsString(ordersDetails));
        }

        log.info("아이템명 확인: {}", resourceShop.getItemName());
        log.info("아이템 번호 확인: {}", resourceShop.getItemId());

        model.addAttribute("shop", resourceShop);
        model.addAttribute("shop2", new ObjectMapper().writeValueAsString(resourceShop));

        model.addAttribute("reviewList", reviewList);
        model.addAttribute("reviewList2", new ObjectMapper().writeValueAsString(reviewList));
        model.addAttribute("currentPage", 1); // 현재 페이지는 1로 설정
        model.addAttribute("totalPages", totalPages);
        return path + "detail";
    }
    // --------------------------------조회--------------------------------------------------

    // -----------------------------------상품 등록 및
    // 수정-----------------------------------------------
    @GetMapping("{roleId}/add/{userId}")
    String addResourcePage(@PathVariable Long roleId, @PathVariable Long userId, Model model, HttpSession session) {
        List<ResourceCategory> category = resourceShopService.addResourcePage();

        // 단순 값넘어오는거 확인용 -------------------------------------------------------
        for (int a = 0; a < category.size(); a++) {
            ResourceCategory rc = category.get(a);
            log.info("리소스 사이즈 : {}" + category.size() + "리소스 1차 번호: {}" + rc.getResourceCategoryId());
            List<ResourceSubCategory> sc = rc.getResourceSubCategory();
            for (ResourceSubCategory rsc : sc) {
                Long id = rsc.getResourceSubCategoryId();
                String name = rsc.getResourceSubCategoryName();

                log.info("2차 카테고리 번호: {}" + id);
                log.info("2차 카테고리 이름: {}" + name);
            }
        }
        // --------------------------------------------------------------------------

        model.addAttribute("category", category);
        return path + "add";
    }

    // 리소스 상품 등록
    @PostMapping("{roleId}/add/{userId}")
    String uploadResource(@PathVariable Long roleId, @PathVariable Long userId, Market market,
            @RequestParam(value = "resourceFile", required = false) List<MultipartFile> resourceFile,
            @RequestParam(value = "resourceImage", required = false) MultipartFile resourceImage,
            Model model) throws Exception {

        resourceShopService.addResource(userId, market, resourceFile, resourceImage, model);

        return path + "add";
    }

    // 리소스 상품 수정
    @PostMapping("/updateMyResource/{itemId}")
    String updateResource(@PathVariable Long itemId,
            Market market, @RequestParam MultipartFile file, Model model) throws Exception {

        resourceShopService.updateMyResource(itemId, market, file, model);

        return path + "updateMyResource";
    }
    // -----------------------------------상품 등록 및
    // 수정-----------------------------------------------

    // -----------------------------------리소스 상품
    // 구매----------------------------------------------
    @PostMapping("/detail/{userId}/{itemId}")
    @ResponseBody // responseBody를 사용하면 문자열을 반환하게됨
    public String buyResource(@PathVariable Long userId, @PathVariable Long itemId,
            @RequestBody List<ResourceShop> shop)
            throws Exception {

        log.info("구매자 이름: {}", userId);
        log.info("구매 아이템 번호: {}", itemId);
        ordersService.buyResource(userId, itemId, shop);
        log.info("구매 완료");
        return "success";
    }
    // -----------------------------------리소스 상품
    // 구매----------------------------------------------

    // --------------------------------로그인 한 유저가 등록한
    // 상품-------------------------------------
    @GetMapping("myResources/{userId}")
    public String myResources(@PathVariable Long userId, Model model) {
        List<ResourceCategory> category = resourceShopService.myResources(userId);
        log.info("내가 등록한 상품 리스트: {}", category);
        model.addAttribute("list", category);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String userAsString;
            userAsString = objectMapper.writeValueAsString(category);
            model.addAttribute("list2", userAsString);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return path + "myResources";
    }

    // 내 상품 상세
    @GetMapping("/salesDetail/{itemId}")
    public String salesResources() {

        return path + "salesDetail";
    }

    // 내 판매 내역(내 상품에 대해서 판매된 최근순으로 조회)
    // 내 상품 상세
    @GetMapping("/mySalesList/{userId}")
    public String mySalesList() {

        return path + "mySalesList";
    }

    // ----------------------------------로그인 한 유저가 등록한
    // 상품-----------------------------------

    /*
     * @ResponseBody //responseBody를 사용하면 문자열을 반환하게됨(spring입장에서는 view가 아닌 데이터로 인식하고
     * 클라이언트로 전송함)
     *
     * @PutMapping("/update") public String updateResource(@RequestBody Cart cart )
     * throws Exception { resourceShopService.updateResource(cart); return "OK"; }
     */

}
