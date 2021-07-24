package com.newborne.yizhi.service;


import com.newborne.yizhi.entity.node.Friend;
import com.newborne.yizhi.entity.relationship.FriendRelationship;
import com.newborne.yizhi.pojo.FriendInfo;

/**
 * The interface Friend service.
 */
public interface FriendService {

    /**
     * Add friend friend.
     *
     * @param friend the friend
     * @return the friend
     */
    Friend addFriend(Friend friend);

    /**
     * Add friend relationship friend relationship.
     *
     * @param start      the start
     * @param end        the end
     * @param similarity the similarity
     * @param remark     the remark
     * @return the friend relationship
     */
    FriendRelationship addFriendRelationship(Friend start, Friend end, Double similarity, String remark);

    /**
     * Find by id friend.
     *
     * @param uuid the uuid
     * @return the friend
     */
    Friend findByUuid(Long uuid);

    /**
     * Find all iterable.
     *
     * @return the iterable
     */
    Iterable<Friend> findAll();
}
