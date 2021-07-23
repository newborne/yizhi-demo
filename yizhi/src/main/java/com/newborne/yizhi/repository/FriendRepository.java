package com.newborne.yizhi.repository;


import com.newborne.yizhi.entity.node.Friend;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface FriendRepository extends Neo4jRepository<Friend, Long> {
    Friend findById(long id);
    @Query("MATCH p =(n:FriendND)-[r:Relationship]->(m:FriendND) WHERE m.name=$name RETURN n")
    List<Friend> findRelationshipByFriendND(String name);
    Friend findByName(String name);
}
