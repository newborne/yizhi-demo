package com.newborne.yizhi.controller;

import com.newborne.yizhi.pojo.FriendRecommend;
import com.newborne.yizhi.response.ApiResponse;
import com.newborne.yizhi.service.FriendRecommendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The type Friend recommend controller.
 */
@Controller
@RequestMapping("/api/friendRecommend")
@Api(tags = "朋友推荐（MongoDB）")
@CrossOrigin
@Slf4j
public class FriendRecommendController {

    @Autowired
    private FriendRecommendService friendRecommendService;

    /**
     * Find all by end api response.
     * end 是mongdb 里面存好的 是spark 算出来的额
     * @param end the end
     * @return the api response
     */
    @GetMapping("/{end}")
    @ResponseBody
    @ApiOperation("查-by-end")
    public Object findFriendRecommendByEnd(@PathVariable Long end) {
        log.info("end {}",end);
        List<FriendRecommend> friendRecommendByEnd =
                friendRecommendService.findFriendRecommendByEnd(end);
        log.info("friendRecommendByEnd {}",friendRecommendByEnd);
        return ApiResponse.success(  friendRecommendByEnd);
    }
}
