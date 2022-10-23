package com.newborne.yizhi.service.impl;


import com.newborne.yizhi.entity.node.Friend;
import com.newborne.yizhi.entity.relationship.FriendRelationship;
import com.newborne.yizhi.repository.FriendRelationshipRepository;
import com.newborne.yizhi.repository.FriendRepository;
import com.newborne.yizhi.service.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * The type Friend service.
 */
@Slf4j
@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private FriendRelationshipRepository relationshipRepository;

    @Override
    public Friend addFriend(Friend friend) {
        return friendRepository.save(friend);
    }

    @Override
    public FriendRelationship addFriendRelationship(Friend start, Friend end, Double similarity, String remark) {
        FriendRelationship relationship = FriendRelationship.builder().
                start(start).end(end).similarity(similarity).remark(remark).build();
//        friendRepository.deleteAll();;
        log.info("relationship {}",relationship);
        return relationshipRepository.save(relationship);
    }

    @Override
    public Friend findByUuid(Long uuid) {
        return friendRepository.findByUuid(uuid);
    }

    public Object deleteAll() {
        friendRepository.deleteAll();
        return true;
    }

    @Override
    public Iterable<Friend> findAll() {
        return friendRepository.findAll();
    }
}
