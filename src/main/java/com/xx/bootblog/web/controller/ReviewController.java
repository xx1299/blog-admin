package com.xx.bootblog.web.controller;

import com.xx.bootblog.domain.Review;
import com.xx.bootblog.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("comment")
    @ResponseBody
    public boolean comment(Review review){
        try {
            reviewService.comment(review);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
