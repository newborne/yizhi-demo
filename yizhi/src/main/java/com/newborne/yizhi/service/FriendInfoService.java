package com.newborne.yizhi.service;


import com.newborne.yizhi.pojo.FriendInfo;

import java.util.List;

/**
 * 示例业务
 * @author 刘博文
 * @date 2021/07/20 20:43
 */
public interface FriendInfoService {

    /**
     * 增
     *
     * @author 刘博文
     * @date 2021/07/20 20:43
     */
    int create(FriendInfo friendInfo);

    /**
     * 查-by-id
     *
     * @author 刘博文
     * @date 2021/07/20 20:43
     */
    FriendInfo findById(Integer id);

    /**
     * 查-所有
     *
     * @author 刘博文
     * @date 2021/07/20 20:43
     */
    List<FriendInfo> findAll();
}
