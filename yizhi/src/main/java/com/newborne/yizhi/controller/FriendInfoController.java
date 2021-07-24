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
 * The type Friend info controller.
 */
@Controller
@RequestMapping("/api/friendInfo")
@Api(tags = "朋友详情（MySql）")
@CrossOrigin
public class FriendInfoController {

    @Autowired
    private FriendInfoService friendInfoService;

    /**
     * Create api response.
     *
     * @param friendInfo the friend info
     * @return the api response
     */
    @PostMapping("/create")
    @ResponseBody
    @ApiOperation("增")
    public ApiResponse<Integer> create(FriendInfo friendInfo) {
        return  ApiResponse.success(friendInfoService.create(friendInfo));
    }

    /**
     * Find by id api response.
     *
     * @param id the id
     * @return the api response
     */
    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation("查-by-id")
    public ApiResponse<FriendInfo> findById(@PathVariable Integer id) {
        return ApiResponse.success(friendInfoService.findById(id));
    }

    /**
     * Find all api response.
     *
     * @return the api response
     */
    @GetMapping("/findAll")
    @ResponseBody
    @ApiOperation("查-全部")
    public ApiResponse<FriendInfo> findAll() {
        return ApiResponse.success(friendInfoService.findAll());
    }
}
