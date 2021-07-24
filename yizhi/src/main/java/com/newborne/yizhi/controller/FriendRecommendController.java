package com.newborne.yizhi.controller;

import com.newborne.yizhi.pojo.FriendRecommend;
import com.newborne.yizhi.response.ApiResponse;
import com.newborne.yizhi.service.FriendRecommendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * The type Friend recommend controller.
 */
@Controller
@RequestMapping("/api/friendRecommend")
@Api(tags = "朋友推荐（MongoDB）")
@CrossOrigin
public class FriendRecommendController {

    @Autowired
    private FriendRecommendService friendRecommendService;

    /**
     * Find all by end api response.
     *
     * @param end the end
     * @return the api response
     */
    @GetMapping("/{end}")
    @ResponseBody
    @ApiOperation("查-by-end")
    public ApiResponse<FriendRecommend> findFriendRecommendByEnd(@PathVariable Long end) {
        return ApiResponse.success(friendRecommendService.findFriendRecommendByEnd(end));
    }
}
