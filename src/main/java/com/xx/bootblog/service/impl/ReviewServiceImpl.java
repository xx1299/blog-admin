package com.xx.bootblog.service.impl;

import com.xx.bootblog.domain.Review;
import com.xx.bootblog.mapper.ReviewMapper;
import com.xx.bootblog.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    @Override
    public void comment(Review review) {
        review.setRtime(new Date());
        reviewMapper.insert(review);
    }
}
