package com.newborne.yizhi.repository;

import com.newborne.yizhi.entity.relationship.FriendRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface FriendRelationshipRepository extends Neo4jRepository<FriendRelationship, Long> {
}
