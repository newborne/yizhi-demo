package com.newborne.yizhi.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * The type Friend info.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendInfo {

    private Long id;

    // 唯一id
    @TableField(exist = false)
    private Long uuid;

    // 联系方式
    private String mobile;

    // 姓名
    private String name;

    // 用户年龄
    private Integer age;

    // 性别，1-男，2-女，3-未知
    private Integer sex;

    // 城市
    private String city;

    // 备注
    private String remark;
}
