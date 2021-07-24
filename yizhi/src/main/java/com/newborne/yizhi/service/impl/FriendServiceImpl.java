package com.newborne.yizhi.service.impl;


import com.newborne.yizhi.entity.node.Friend;
import com.newborne.yizhi.entity.relationship.FriendRelationship;
import com.newborne.yizhi.repository.FriendRelationshipRepository;
import com.newborne.yizhi.repository.FriendRepository;
import com.newborne.yizhi.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private FriendRelationshipRepository relationshipRepository;

    @Override
    public FriendRelationship addFriendRelationship(Friend start, Friend end, Double similarity, String remark) {
        FriendRelationship relationship = FriendRelationship.builder().start(start).end(end).similarity(similarity).remark(remark).build();
        return relationshipRepository.save(relationship);
    }

    @Override
    public Friend findById(long id) {
        return friendRepository.findById(id);
    }

    @Override
    public Iterable<Friend> findAll() {
        return friendRepository.findAll();
    }
}
