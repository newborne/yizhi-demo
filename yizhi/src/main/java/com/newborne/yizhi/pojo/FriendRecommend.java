package com.newborne.yizhi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//lombok注解，简化javabean编写（Structure中就有了get和set，不需要自己编写）
//全参数
@AllArgsConstructor
//没有参数
@NoArgsConstructor
@Data
//@Document注解是spring Data mongodb提供的一个注解。
@Document(collection = "friend_recommend")
//服务之间的传输，需要实现序列化接口
public class FriendRecommend implements java.io.Serializable {

    private static final long serialVersionUID = -5454047825686790281L;

    @Id
    private ObjectId id;

    // 推荐
    @Indexed
    private Long start;

    // 推荐给
    private Long end;

    // 相似度
    @Indexed
    private Double similarity;

    // 备注
    private String remark;
}
