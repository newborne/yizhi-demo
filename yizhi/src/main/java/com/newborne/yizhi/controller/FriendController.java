package com.newborne.yizhi.controller;

import com.newborne.yizhi.entity.node.Friend;
import com.newborne.yizhi.pojo.FriendInfo;
import com.newborne.yizhi.pojo.FriendRecommend;
import com.newborne.yizhi.response.ApiResponse;
import com.newborne.yizhi.service.FriendInfoService;
import com.newborne.yizhi.service.FriendRecommendService;
import com.newborne.yizhi.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The type Friend controller.
 */
@Controller
@RequestMapping("/api/friend")
@Api(tags = "朋友（Neo4j）")
@CrossOrigin
public class FriendController {

    @Autowired
    private FriendInfoService friendInfoService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private FriendRecommendService friendRecommendService;

    /**
     * Find by id api response.
     *
     * @param uuid the uuid
     * @return the api response
     */
    @GetMapping("/{uu   id}")
    @ResponseBody
    @ApiOperation("查-by-uuid")
    public ApiResponse<Friend> findById(@PathVariable Long uuid) {
        return ApiResponse.success(friendService.findByUuid(uuid));
    }

    /**
     * Find all api response.
     *
     * @return the api response
     */
    @GetMapping("/findAll")
    @ResponseBody
    @ApiOperation("查-全部")
    public ApiResponse<Friend> findAll() {
        return ApiResponse.success(friendService.findAll());
    }

    /**
     * Save api response.
     *
     * @return the api response
     */
    @GetMapping("/save")
    @ResponseBody
    @ApiOperation("增-结点&联系")
    public ApiResponse save() {
        List<FriendInfo> friendInfos = friendInfoService.findAll();
        for (FriendInfo friendInfo : friendInfos) {
            Friend friend = Friend.builder()
                    .city(friendInfo.getCity())
                    .remark(friendInfo.getRemark())
                    .sex(friendInfo.getSex())
                    .age(friendInfo.getAge())
                    .name(friendInfo.getName())
                    .mobile(friendInfo.getMobile())
                    .uuid(friendInfo.getUuid()).build();
            friendService.addFriend(friend);
        }
        for (FriendInfo friendInfo : friendInfos) {
            List<FriendRecommend> friendRecommends = friendRecommendService.findFriendRecommendByEnd(friendInfo.getId());
            for (FriendRecommend friendRecommend : friendRecommends) {
                Friend start = friendService.findByUuid(friendRecommend.getStart());
                Friend end = friendService.findByUuid(friendInfo.getId());
                friendService.addFriendRelationship(start, end, friendRecommend.getSimilarity(), friendRecommend.getRemark());
            }
        }
        return ApiResponse.success("ok");
    }
}
