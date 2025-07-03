package com.example.test.Controller;

import com.example.test.Model.ReviewSort;
import com.example.test.Model.ShopReview;
import com.example.test.Service.ShopReviewService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                                   @RequestParam("sort") String sort,
                                   Model model) {
        ReviewSort reviewSort = ReviewSort.builder()
                .itemId(itemId)
                .sort(sort)
                .build();
        List<ShopReview> sortedList = shopReviewService.getReviewsSorted(reviewSort);
        model.addAttribute("reviewList", sortedList);
        return "shop/fragment/reviewList :: reviewList"; // JSP fragment 반환
    }

}
