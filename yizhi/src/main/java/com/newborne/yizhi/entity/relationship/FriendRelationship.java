package com.newborne.yizhi.entity.relationship;


import com.newborne.yizhi.entity.node.Friend;
import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;


@Data
@Builder
@RelationshipEntity(type = "Relationship")
public class FriendRelationship {
    @Id
    @GeneratedValue
    private Long id;

    // 推荐
    @StartNode
    private Friend start;

    // 推荐给
    @EndNode
    private Friend end;

    //相似度
    private Double similarity;

    // 备注
    private String remark;

}
