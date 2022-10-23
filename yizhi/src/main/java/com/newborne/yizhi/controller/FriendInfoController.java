package com.newborne.yizhi.controller;

import com.newborne.yizhi.pojo.FriendInfo;
import com.newborne.yizhi.response.ApiResponse;
import com.newborne.yizhi.service.FriendInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
//import org.slf4j.Logger;
import org.apache.spark.sql.catalyst.plans.logical.Except;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
//import java.util.logging.Logger;


/**
 * The type Friend info controller.
 */
@Controller
@RequestMapping("/api/friendInfo")
@Api(tags = "朋友详情（MySql）")
@CrossOrigin
@Slf4j
public class FriendInfoController {

    @Autowired
    private FriendInfoService friendInfoService;

    /**
     * Create api response.
     * mysql insert
     * @param friendInfo the friend info
     * @return the api response
     */
    @PostMapping("/create")
    @ResponseBody
    @ApiOperation("增")
    public ApiResponse<Integer> create(FriendInfo friendInfo) {
        return  ApiResponse.success(friendInfoService.create(friendInfo));
    }

    @Autowired
//    private ThreadPoolTaskExecutor ThreadPoolA;
    private ThreadPoolTaskExecutor pool;


    /**
     * 获取a数据
     *
     * @param map
     * @return
     */
    private static List<String> getCDate(Map<String, Object> map) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add("c" + i);
        }

        return list;
    }

    /**
     * 获取b数据
     *
     * @param map
     * @return
     */
    private static List<String> getBDate(Map<String, Object> map) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add("b" + i);
        }
        return list;
    }

    /**
     * 获取c数据
     *
     * @param map
     * @return
     */
    private static List<String> getADate(Map<String, Object> map) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add("a" + i);
        }
        return list;
    }


    @PostMapping("/getByLatchDown")
    @ResponseBody
    @ApiOperation("getByLatchDown")
    public  Object getByLatchDown(@RequestBody  FriendInfo friendInfo) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(1);
        map.put("date", "2020-12-27");
        CountDownLatch latch = new CountDownLatch(3);

//         List<String> a = new ArrayList<>();
//
//         List<String> b = new ArrayList<>();
//
//         List<String> c = new ArrayList<>();
//        log;
       Logger logger=  log;
//        log
//        Logger logger = LoggerFactory.getLogger("1");

        Future<List<String> > future =    pool.submit(() -> {
//            a = getADate(map);
//            List<String> aDate = getADate(map);
//            latch.countDown();
//            System.out.println("=========================a" + latch.getCount());
//            return


            try {
//                System.out.println("=========================a" + latch.getCount());
//                logger.info("thread2->doWork");
                System.out.println("a");
                System.out.println( "latch.getCount()");
                System.out.println( latch.getCount());
                Thread.sleep(1000L);
                return getADate(map);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }finally {
                System.out.println("a end");
//                logger.info("thread2->end");
                //线程数-1
                latch.countDown();

            }
//————————————————
//            版权声明：本文为CSDN博主「风飘一叶落」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//            原文链接：https://blog.csdn.net/qq_22611671/article/details/102918842

        });



        Future<List<String> > futureB =    pool.submit(() -> {
//            a = getADate(map);
//            List<String> aDate = getADate(map);
//            latch.countDown();
//            System.out.println("=========================a" + latch.getCount());
//            return


            try {
//                System.out.println("=========================a" + latch.getCount());
//                logger.info("thread2->doWork");
                System.out.println("B");

                System.out.println( "latch.getCount()");
                System.out.println( latch.getCount());
                Thread.sleep(1000L);


                return getBDate(map);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }finally {
                System.out.println("b end");
//                logger.info("thread2->end");
                //线程数-1
                latch.countDown();

            }
//————————————————
//            版权声明：本文为CSDN博主「风飘一叶落」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//            原文链接：https://blog.csdn.net/qq_22611671/article/details/102918842

        });

        Future<List<String> > futureC =    pool.submit(() -> {
//            a = getADate(map);
//            List<String> aDate = getADate(map);
//            latch.countDown();
//            System.out.println("=========================a" + latch.getCount());
//            return


            try {
//                System.out.println("=========================a" + latch.getCount());
                System.out.println("c futureC");
                System.out.println( "latch.getCount()");
                System.out.println( latch.getCount());
//                logger.info("thread2->doWork");
                Thread.sleep(1000L);
                return getCDate(map);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }finally {
                System.out.println("c end");
//                logger.info("thread2->end");
                //线程数-1
                latch.countDown();

            }
//————————————————
//            版权声明：本文为CSDN博主「风飘一叶落」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//            原文链接：https://blog.csdn.net/qq_22611671/article/details/102918842

        });

//        List<String> list = future.get();

        //省略
//        Future<Long> future = pool.submit(() -> {
//            //省略
//            return 1L;
////            return
//        });

//        pool.submit(() -> {
//            b = getBDate(map);
//            latch.countDown();
//            System.out.println("=========================b" + latch.getCount());
//
//        });
//
//        pool.submit(() -> {
//            c = getCDate(map);
//            latch.countDown();
//            System.out.println("=========================c" + latch.getCount());
//
//        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("=========================" + latch.getCount());
         List<String> sumList = new ArrayList<>();

        sumList.addAll( future.get());
        sumList.addAll(futureB.get());
        sumList.addAll(futureC.get());

//
//        if (!CollectionUtils.isEmpty(a)
//                && !CollectionUtils.isEmpty(b)
//                && !CollectionUtils.isEmpty(c)) {
////            sumList.addAll(a);
////            sumList.addAll(b);
////            sumList.addAll(c);
//
//            sumList.addAll( future.get());
//            sumList.addAll(futureB.get());
//            sumList.addAll(futureC.get());
//            a.clear();
//            b.clear();
//            c.clear();
//
//        }


//        if (CollectionUtils.isEmpty(a)) {
//            System.out.println("a查询结果为空");
//        }
//        if (CollectionUtils.isEmpty(b)) {
//            System.out.println("b查询结果为空");
//        }
//        if (CollectionUtils.isEmpty(c)) {
//            System.out.println("c查询结果为空");
//        }
        System.out.println("sumList");
        System.out.println(sumList);
        long end = System.currentTimeMillis();
        System.out.println("===============>>"+(end-start));
        pool.shutdown();
//        return ReturT
        return ApiResponse.success(sumList);
    }

    @PostMapping("/getALlByLatchDown")
    @ResponseBody
    @ApiOperation("getALlByLatchDown")
    public  Object getALlByLatchDown(@RequestBody  FriendInfo friendInfo) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
//        Map<String, Object> map = new HashMap<>(1);
//        map.put("date", "2020-12-27");
        CountDownLatch latch = new CountDownLatch(3);

//         List<String> a = new ArrayList<>();
//
//         List<String> b = new ArrayList<>();
//
//         List<String> c = new ArrayList<>();
//        log;
//        Logger logger=  log;
//        log
//        Logger logger = LoggerFactory.getLogger("1");

//        Future< List<FriendInfo> > future =    pool.submit(() -> {
//
//            try {
////                System.out.println("=========================a" + latch.getCount());
////                logger.info("thread2->doWork");
//                System.out.println("a");
//                System.out.println( "latch.getCount()");
//                System.out.println( latch.getCount());
//                Thread.sleep(1000L);
//                List<FriendInfo> all = friendInfoService.findAll();
//                return all;
////                return getADate(map);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                return null;
//            }finally {
//                System.out.println("a end");
////                logger.info("thread2->end");
//                //线程数-1
//                latch.countDown();
//
//            }
////————————————————
////            版权声明：本文为CSDN博主「风飘一叶落」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
////            原文链接：https://blog.csdn.net/qq_22611671/article/details/102918842
//
//        });
//        Callable 取名字
        Callable<List<FriendInfo>> callable = new Callable<List<FriendInfo>>() {
            @Override
            public List<FriendInfo> call() throws Exception {
//                return null;
                try {
//                    get
//                System.out.println("=========================a" + latch.getCount());
//                logger.info("thread2->doWork");
                    System.out.println("a");
                    System.out.println( "latch.getCount()");
                    System.out.println( latch.getCount());
//                    Thread.sleep(1000L);
                    List<FriendInfo> all = friendInfoService.findAll();
                    return all;
//                return getADate(map);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                finally {
                    System.out.println("a end");
//                logger.info("thread2->end");
                    //线程数-1
                    latch.countDown();

                }
            }
        };
//        pool.submit(new Callable< List<FriendInfo>>() {
//            @Override
//            public List<FriendInfo> call() throws Exception {
//                return null;
//            }
//        });

        Future<List<FriendInfo>> futureA = pool.submit(callable);
        Future<List<FriendInfo>> futureB = pool.submit(callable);
        Future<List<FriendInfo>> futureC = pool.submit(callable);

//        Future<List<String> > futureB =    pool.submit(() -> {
////            a = getADate(map);
////            List<String> aDate = getADate(map);
////            latch.countDown();
////            System.out.println("=========================a" + latch.getCount());
////            return
//
//
//            try {
////                System.out.println("=========================a" + latch.getCount());
////                logger.info("thread2->doWork");
//                System.out.println("B");
//
//                System.out.println( "latch.getCount()");
//                System.out.println( latch.getCount());
//                Thread.sleep(1000L);
//
//
//                return getBDate(map);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                return null;
//            }finally {
//                System.out.println("b end");
////                logger.info("thread2->end");
//                //线程数-1
//                latch.countDown();
//
//            }
////————————————————
////            版权声明：本文为CSDN博主「风飘一叶落」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
////            原文链接：https://blog.csdn.net/qq_22611671/article/details/102918842
//
//        });

//        Future<List<String> > futureC =    pool.submit(() -> {
////            a = getADate(map);
////            List<String> aDate = getADate(map);
////            latch.countDown();
////            System.out.println("=========================a" + latch.getCount());
////            return
//
//
//            try {
////                System.out.println("=========================a" + latch.getCount());
//                System.out.println("c futureC");
//                System.out.println( "latch.getCount()");
//                System.out.println( latch.getCount());
////                logger.info("thread2->doWork");
//                Thread.sleep(1000L);
//                return getCDate(map);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                return null;
//            }finally {
//                System.out.println("c end");
////                logger.info("thread2->end");
//                //线程数-1
//                latch.countDown();
//
//            }
////————————————————
////            版权声明：本文为CSDN博主「风飘一叶落」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
////            原文链接：https://blog.csdn.net/qq_22611671/article/details/102918842
//
//        });

//        List<String> list = future.get();

        //省略
//        Future<Long> future = pool.submit(() -> {
//            //省略
//            return 1L;
////            return
//        });

//        pool.submit(() -> {
//            b = getBDate(map);
//            latch.countDown();
//            System.out.println("=========================b" + latch.getCount());
//
//        });
//
//        pool.submit(() -> {
//            c = getCDate(map);
//            latch.countDown();
//            System.out.println("=========================c" + latch.getCount());
//
//        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("=========================" );
        System.out.println("结束力");
        System.out.println("latch.getCount()");
        System.out.println(latch.getCount());
//        System.out.println("=========================" + latch.getCount());
//        List<String> sumList = new ArrayList<>();
        List<FriendInfo>allList= new ArrayList<>();
        allList.addAll( futureA.get());
        allList.addAll(futureB.get());
        allList.addAll(futureC.get());


//        sumList.addAll( future.get());
//        sumList.addAll(futureB.get());
//        sumList.addAll(futureC.get());

//
//        if (!CollectionUtils.isEmpty(a)
//                && !CollectionUtils.isEmpty(b)
//                && !CollectionUtils.isEmpty(c)) {
////            sumList.addAll(a);
////            sumList.addAll(b);
////            sumList.addAll(c);
//
//            sumList.addAll( future.get());
//            sumList.addAll(futureB.get());
//            sumList.addAll(futureC.get());
//            a.clear();
//            b.clear();
//            c.clear();
//
//        }


//        if (CollectionUtils.isEmpty(a)) {
//            System.out.println("a查询结果为空");
//        }
//        if (CollectionUtils.isEmpty(b)) {
//            System.out.println("b查询结果为空");
//        }
//        if (CollectionUtils.isEmpty(c)) {
//            System.out.println("c查询结果为空");
//        }
//        System.out.println("sumList");
//        System.out.println(sumList);
        long end = System.currentTimeMillis();
//        System.out.println("===============>>"+(end-start));
        System.out.println("用时间");
        System.out.println((end-start)+"ms");
        pool.shutdown();
//        return ReturT
//        return ApiResponse.success(sumList);
        return ApiResponse.success(allList);
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
