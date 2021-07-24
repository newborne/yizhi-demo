package com.newborne.yizhi.service.impl;

import com.newborne.yizhi.pojo.FriendRecommend;
import com.newborne.yizhi.repository.FriendRecommendRepository;
import com.newborne.yizhi.service.FriendRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRecommendServiceImpl implements FriendRecommendService {

    @Autowired
    private FriendRecommendRepository friendRecommendRepository;

    // 获取start
    @Override
    public List<FriendRecommend> findAllByEnd(Long end) {
        return friendRecommendRepository.findAllByEnd(end);
    }

}
