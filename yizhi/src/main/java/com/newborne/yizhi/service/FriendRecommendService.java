package com.newborne.yizhi.service;


import com.newborne.yizhi.pojo.FriendRecommend;

import java.util.List;

public interface FriendRecommendService {

    // 获取推荐
    List<FriendRecommend> findAllByEnd(Long end);

}
