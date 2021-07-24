package com.newborne.yizhi.repository;


import com.newborne.yizhi.entity.node.Friend;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends Neo4jRepository<Friend, Long> {
    Friend findById(long id);
}
