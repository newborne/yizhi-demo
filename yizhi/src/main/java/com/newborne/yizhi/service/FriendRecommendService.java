package com.newborne.yizhi.service;


import com.newborne.yizhi.pojo.FriendRecommend;

import java.util.List;

/**
 * The interface Friend recommend service.
 */
public interface FriendRecommendService {

    /**
     * Find all by end list.
     *
     * @param end the end
     * @return the list
     */
// 获取推荐
    List<FriendRecommend> findFriendRecommendByEnd(Long end);

}
