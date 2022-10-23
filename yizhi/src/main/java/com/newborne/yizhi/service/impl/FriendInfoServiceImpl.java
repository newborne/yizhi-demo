package com.newborne.yizhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newborne.yizhi.mapper.FriendInfoMapper;
import com.newborne.yizhi.pojo.FriendInfo;
import com.newborne.yizhi.service.FriendInfoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Friend info service.
 * mysql
 */
@Service
public class FriendInfoServiceImpl extends ServiceImpl<FriendInfoMapper, FriendInfo> implements FriendInfoService {

    @Override
    public int create(FriendInfo friendInfo) {

        friendInfo.setAge(18);
        friendInfo.setCity("上海市");
        friendInfo.setMobile("18018594399");
        friendInfo.setSex(1);
        return baseMapper.insert(friendInfo);
    }

    @Override
    @Cacheable(value = "friendInfo", keyGenerator = "keyGenerator")
    public FriendInfo findById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    @Cacheable(value = "friendInfo", keyGenerator = "keyGenerator")
    public List<FriendInfo> findAll() {
        QueryWrapper<FriendInfo> queryWrapper = new QueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }
}
