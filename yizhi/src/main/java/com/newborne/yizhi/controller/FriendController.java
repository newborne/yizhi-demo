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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author 刘博文
 * @date 2021/07/20 20:43
 */
@Controller
@RequestMapping("/api/friend")
@Api(tags = "朋友（neo4j）")
@CrossOrigin
public class FriendController {

    @Autowired
    private FriendInfoService friendInfoService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private FriendRecommendService friendRecommendService;

    /**
     * @author 刘博文
     * @date 2021/07/20 20:43
     */
    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation("查-by-id")
    public ApiResponse<Friend> findById(@PathVariable Long id) {
        return ApiResponse.success(friendService.findById(id));
    }

    /**
     * @author 刘博文
     * @date 2021/07/20 20:43
     */
    @GetMapping("/findAll")
    @ResponseBody
    @ApiOperation("查-全部")
    public ApiResponse<Friend> findAll() {
        return ApiResponse.success(friendService.findAll());
    }

    /**
     * @return
     * @author 刘博文
     * @date 2021/07/20 20:43
     */
    @GetMapping("/saveRelationship")
    @ResponseBody
    @ApiOperation("增-联系")
    public ApiResponse save() {
        List<FriendInfo> friendInfos = friendInfoService.findAll();
        for (FriendInfo friendInfo : friendInfos) {
            List<FriendRecommend> friendRecommends = friendRecommendService.findAllByEnd(friendInfo.getId());
            for (FriendRecommend friendRecommend : friendRecommends) {
                Friend start = friendService.findById(friendRecommend.getStart() - 1);
                Friend end = friendService.findById(friendInfo.getId() - 1);
                friendService.addFriendRelationship(start, end, friendRecommend.getSimilarity(), friendRecommend.getRemark());
            }
        }
        return ApiResponse.success("ok");
    }
}
