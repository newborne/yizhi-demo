package com.newborne.yizhi.controller;

import com.newborne.yizhi.entity.node.Friend;
import com.newborne.yizhi.pojo.FriendInfo;
import com.newborne.yizhi.pojo.FriendRecommend;
import com.newborne.yizhi.repository.FriendRepository;
import com.newborne.yizhi.response.ApiResponse;
import com.newborne.yizhi.service.FriendInfoService;
import com.newborne.yizhi.service.FriendRecommendService;
import com.newborne.yizhi.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


/**
 * The type Friend controller.
 */
@Controller
@RequestMapping("/api/friend")
@Api(tags = "朋友（Neo4j）")
@CrossOrigin
@Slf4j
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

    @PostMapping("/deleteAll")
    @ResponseBody
    @ApiOperation("deleteAll")
    public Object deleteAll() {
        return ApiResponse.success(friendService.deleteAll());
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

    @Resource
    FriendRepository friendRepository;

    /**
     * Save api response.
     *
     * @return the api response
     */
    @GetMapping("/save")
    @ResponseBody
    @ApiOperation("增-结点&联系")
    public ApiResponse save() {
//        db mysql
        List<FriendInfo> friendInfos = friendInfoService.findAll();
        log.info("msyql 保存到neo4j");
        log.info("friendInfos {}",friendInfos);
        for (FriendInfo friendInfo : friendInfos) {
            Friend friend = Friend.builder()
                    .city(friendInfo.getCity())
                    .remark(friendInfo.getRemark())
                    .sex(friendInfo.getSex())
                    .age(friendInfo.getAge())
                    .name(friendInfo.getName())
                    .mobile(friendInfo.getMobile())
                    .uuid(friendInfo.getUuid()).build();
//            Neo4jRepository save
            friendService.addFriend(friend);
        }
        log.info("朋友推荐");
        for (FriendInfo friendInfo : friendInfos) {
            log.info("最推荐的几个人");
            List<FriendRecommend> friendRecommends = friendRecommendService.findFriendRecommendByEnd(friendInfo.getId());
            log.info("friendRecommends {}",friendRecommends);
            for (FriendRecommend friendRecommend : friendRecommends) {
//                Friend start = friendService.findByUuid(friendRecommend.getStart());
//                Friend start = friendService.find(friendRecommend.getStart());
                Optional<Friend> byId = friendRepository.findById(friendRecommend.getStart());
                if (!byId.isPresent()) {
                    log.info("没有start 这是不对的  这个id的没有");
                    log.info("friendRecommend {}",friendRecommend);
                    break;
                }
                Friend start = byId.get();
//                Friend end = friendRepository.findById(friendInfo.getId()).get();
                Optional<Friend> friendEndOp = friendRepository.findById(friendInfo.getId());
                if (!friendEndOp.isPresent()) {
                    log.info("没有 end 这是不对的  这个id的没有");
                    log.info("friendRecommend {}",friendRecommend);
                    break;
                }
                Friend end = friendEndOp.get();
//                Friend end = friendService.findByUuid(friendInfo.getId());
                friendService.addFriendRelationship(start, end, friendRecommend.getSimilarity(), friendRecommend.getRemark());
            }
        }
        return ApiResponse.success("ok");
    }
}
