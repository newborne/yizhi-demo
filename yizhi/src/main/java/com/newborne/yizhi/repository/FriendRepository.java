package com.newborne.yizhi.repository;


import com.newborne.yizhi.entity.node.Friend;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Friend repository.
 */
@Repository
public interface FriendRepository extends Neo4jRepository<Friend, Long> {
    /**
     * Find by id friend.
     *
     * @param uuid the uuid
     * @return the friend
     */
    Friend findByUuid(Long uuid);
}
