package com.newborne.yizhi;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 查询三个不同表，查询的结果进行合并
 *
 * @author fdy
 */
public class ThreadOneToMany {
    static ThreadFactory namedThreadFactory = Executors.defaultThreadFactory();
    static ExecutorService pool = new ThreadPoolExecutor(3, 3,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    static List<String> a = new ArrayList<>();

    static List<String> b = new ArrayList<>();

    static List<String> c = new ArrayList<>();

    static List<String> sumList = new ArrayList<>();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(1);
        map.put("date", "2020-12-27");
        CountDownLatch latch = new CountDownLatch(3);
//        CountDownLatch 结合 springboot repo
        pool.submit(() -> {
            a = getADate(map);
            latch.countDown();
            System.out.println("=========================a" + latch.getCount());

        });
        pool.submit(() -> {
            b = getBDate(map);
            latch.countDown();
            System.out.println("=========================b" + latch.getCount());

        });

        pool.submit(() -> {
            c = getCDate(map);
            latch.countDown();
            System.out.println("=========================c" + latch.getCount());

        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("=========================" + latch.getCount());

        if (!CollectionUtils.isEmpty(a)
                && !CollectionUtils.isEmpty(b)
                && !CollectionUtils.isEmpty(c)) {
            sumList.addAll(a);
            sumList.addAll(b);
            sumList.addAll(c);
            a.clear();
            b.clear();
            c.clear();

        }
        if (CollectionUtils.isEmpty(a)) {
            System.out.println("a查询结果为空");
        }
        if (CollectionUtils.isEmpty(b)) {
            System.out.println("b查询结果为空");
        }
        if (CollectionUtils.isEmpty(c)) {
            System.out.println("c查询结果为空");
        }
        System.out.println(sumList);
        long end = System.currentTimeMillis();
        System.out.println("===============>>"+(end-start));
        pool.shutdown();
    }

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

}



