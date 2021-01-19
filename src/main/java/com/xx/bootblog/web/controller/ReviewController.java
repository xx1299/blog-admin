package com.xx.bootblog.web.controller;

import com.xx.bootblog.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

}
