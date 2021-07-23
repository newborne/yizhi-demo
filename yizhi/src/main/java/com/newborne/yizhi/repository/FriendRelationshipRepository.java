package com.newborne.yizhi.repository;

import com.newborne.yizhi.entity.relationship.FriendRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRelationshipRepository extends Neo4jRepository<FriendRelationship, Long> {
}
