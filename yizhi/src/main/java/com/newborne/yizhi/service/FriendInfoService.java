package com.newborne.yizhi.service;


import com.newborne.yizhi.pojo.FriendInfo;

/**
 * 示例业务
 * @author 刘博文
 * @date 2021/07/20 20:43
 */
public interface FriendInfoService {

    /**
     * 创建
     * @author 刘博文
     * @date 2021/07/20 20:43
     */
    int create(FriendInfo friendInfo);
    /**
     * 主键查询
     * @author 刘博文
     * @date 2021/07/20 20:43
     */
    FriendInfo findById(Integer id);


}
