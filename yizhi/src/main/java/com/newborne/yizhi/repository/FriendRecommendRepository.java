package com.newborne.yizhi.repository;

import com.newborne.yizhi.pojo.FriendRecommend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Friend recommend repository.
 */
@Repository
public interface FriendRecommendRepository extends MongoRepository<FriendRecommend,String> {
    /**
     * Find all by end list.
     *
     * @param end the end
     * @return the list
     */
// 获取所有
    List<FriendRecommend> findTop3ByEndOrderBySimilarityDesc(Long end);
}
