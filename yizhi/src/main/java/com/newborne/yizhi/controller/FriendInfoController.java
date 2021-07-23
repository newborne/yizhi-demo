package com.newborne.yizhi.controller;

import com.newborne.yizhi.pojo.FriendInfo;
import com.newborne.yizhi.response.ApiResponse;
import com.newborne.yizhi.service.FriendInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author 刘博文
 * @date 2021/07/20 20:43
 */
@Controller
@RequestMapping("/api/friendInfo")
@Api(tags = "用户模块接口")
@CrossOrigin
public class FriendInfoController {

    @Autowired
    private FriendInfoService friendInfoService;

    /**
     * @author 刘博文
     * @date 2021/07/20 20:43
     */
    @PostMapping("/create")
    @ResponseBody
    @ApiOperation("创建用户")
    public ApiResponse<Integer> create(FriendInfo friendInfo) {
        return  ApiResponse.success(friendInfoService.create(friendInfo));
    }

    /**
     * @author 刘博文
     * @date 2021/07/20 20:43
     */
    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation("根据ID查询用户")
    public ApiResponse<FriendInfo> finById(@PathVariable Integer id) {
        return ApiResponse.success(friendInfoService.findById(id));
    }
}
