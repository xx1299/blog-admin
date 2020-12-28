package com.xx.bootblog.mapper;

import com.xx.bootblog.domain.Review;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReviewMapper {
    int deleteByPrimaryKey(@Param("aid") Integer aid, @Param("uid") Integer uid);

    int insert(Review record);

    Review selectByPrimaryKey(@Param("aid") Integer aid, @Param("uid") Integer uid);

    List<Review> selectAll();

    int updateByPrimaryKey(Review record);

    List<Review> selectByAid(Integer aid);

    Long getTotal();

}