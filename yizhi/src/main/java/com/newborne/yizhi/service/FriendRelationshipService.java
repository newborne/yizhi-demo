package com.newborne.yizhi.service;


import com.newborne.yizhi.entity.node.Friend;
import com.newborne.yizhi.entity.relationship.FriendRelationship;

public interface FriendRelationshipService {
    FriendRelationship addFriendRelationship(Friend start, Friend end, String remark);
    Friend findById(long id);
    Iterable<Friend> findAll();
}
