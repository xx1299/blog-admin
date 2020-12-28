package com.xx.bootblog.service.impl;

import com.xx.bootblog.domain.Information;
import com.xx.bootblog.mapper.ArticleMapper;
import com.xx.bootblog.mapper.ProgramaMapper;
import com.xx.bootblog.mapper.ReviewMapper;
import com.xx.bootblog.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class InformationServiceImpl implements InformationService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ProgramaMapper programaMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public Information getInformation() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Information information = new Information();
        information.setArticleTotal(articleMapper.getTotal());
        information.setProgramaTotal(programaMapper.getTotal());
        information.setLastTime(articleMapper.getLastTime());
        information.setDayView(redisTemplate.opsForHyperLogLog().size(sdf.format(new Date())));
        information.setReviewTotal(reviewMapper.getTotal());
        return information;
    }
}
