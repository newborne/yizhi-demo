package com.newborne.yizhi.repository;

import com.newborne.yizhi.pojo.FriendRecommend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRecommendRepository extends MongoRepository<FriendRecommend,String> {
    // 获取所有
    List<FriendRecommend> findAllByEnd(Long end);
}
