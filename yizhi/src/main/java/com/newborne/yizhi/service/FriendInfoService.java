package com.newborne.yizhi.service;


import com.newborne.yizhi.pojo.FriendInfo;

import java.util.List;

/**
 * The interface Friend info service.
 */
public interface FriendInfoService {

    /**
     * Create int.
     *
     * @param friendInfo the friend info
     * @return the int
     */
    int create(FriendInfo friendInfo);

    /**
     * Find by id friend info.
     *
     * @param id the id
     * @return the friend info
     */
    FriendInfo findById(Integer id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<FriendInfo> findAll();
}
