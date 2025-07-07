package com.example.test.Controller;

import com.example.test.Model.ShopReview;
import com.example.test.Model.Users;
import com.example.test.Service.ShopReviewService;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/shopReview")
public class ShopReviewController {
    @Autowired
    ShopReviewService shopReviewService;

    @PostMapping("/review")
    @ResponseBody
    public String addReview(@RequestBody ShopReview review) {
        log.info("리뷰 작성 요청: {}", review);
        shopReviewService.addReview(review);
        return "success";
    }

    @GetMapping("/sorted/{itemId}")
    public String getSortedReviews(@PathVariable("itemId") Long itemId,
                                   @RequestParam("sortType") String sortType,
                                   @RequestParam(value = "page", defaultValue = "1") int page,
                                   Model model) {
        int pageSize = 5; // 페이지당 리뷰 수
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("itemId", itemId);
        params.put("sortType", sortType);
        params.put("startRow", startRow);
        params.put("endRow", endRow);

        List<ShopReview> sortedList = shopReviewService.getReviewsSorted(params);
        model.addAttribute("reviewList", sortedList);

        Integer totalCount = shopReviewService.getReviewCountByItemId(itemId);
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        int pageGroupSize = 10;
        int startPage = ((page - 1) / pageGroupSize) * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("hasPrev", startPage > 1);
        model.addAttribute("hasNext", endPage < totalPages);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("sortType", sortType);
        return "shop/fragment/reviewList"; // JSP fragment 반환
    }

    @PostMapping("/delete/{reviewId}/{itemId}")
    @ResponseBody
    public String deleteReview(@PathVariable("reviewId") Long reviewId,
                               @PathVariable("itemId") Long itemId,
                               HttpSession session) {
        Users user = (Users) session.getAttribute("member");
        Long userId = user.getUserId();
        Map<String, Object> params = new HashMap<>();
        params.put("reviewId", reviewId);
        params.put("itemId", itemId);
        params.put("userId", userId);
        shopReviewService.deleteReview(params);
        return "success";
    }
}
