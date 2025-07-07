package com.example.test.Controller;

import com.example.test.Model.ShopReview;
import com.example.test.Model.Users;
import com.example.test.Service.ShopReviewService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
        List<ShopReview> sortedList = shopReviewService.getReviewsSorted(itemId, sortType, startRow, endRow);
        model.addAttribute("reviewList", sortedList);

        Integer totalCount = shopReviewService.getReviewCountByItemId(itemId);
        Integer totalPages = (int) Math.ceil(totalCount / 10.0);

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
        shopReviewService.deleteReview(reviewId, itemId, userId);
        return "success";
    }
}
