package com.newborne.yizhi.spark;

import com.mongodb.spark.MongoSpark;
import com.newborne.yizhi.util.string.Convert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.bson.Document;
import org.bson.types.ObjectId;
import scala.Tuple2;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * The type Spark friend recommend.
 * Spark 不要debug
 */
@Slf4j
public class SparkFriendRecommend {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        //加载外部的配置文件，app.properties
        InputStream inputStream = SparkFriendRecommend.class.getClassLoader()
                .getResourceAsStream("app.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        // 构建Spark配置
        SparkConf sparkConf = new SparkConf()
                .setAppName("SparkFriendRecommend")
                .setMaster("local[*]")
                .set("spark.mongodb.output.uri",
                        properties.getProperty("spark.mongodb.output.friend.uri"));

        // 加载mysql数据
        SparkSession sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();
        String url = properties.getProperty("jdbc.url");

        // 设置数据库连接信息
        Properties connectionProperties = new Properties();
        connectionProperties.put("driver", properties.getProperty("jdbc.driver-class-name"));
        connectionProperties.put("user", properties.getProperty("jdbc.username"));
        connectionProperties.put("password", properties.getProperty("jdbc.password"));

        JavaRDD<Row> friendInfoRdd = sparkSession.read().
                jdbc(url, "friend_info", connectionProperties).toJavaRDD();

        log.info("friendInfoRdd {}",friendInfoRdd);
//        friendInfoRdd.for
        int idColNum=0;
        // 用户列表
//        getLong Row package org.apache.spark.sql;
        List<Long> friendIds = friendInfoRdd.map(v -> {
//            int colNum=1;
//            int idColNum=0;

//            public long getLong(int columnIndex) throws SQLException {

//                String idStr = v.getString(colNum);
//                String idStr = v.getString(colNumId);
//            Long aLong = Convert.toLong(idStr);
            long aLong = v.getLong(idColNum);

//            row
            log.info("aLong {}",aLong);
//            log.info("idStr {}",idStr);
            return  aLong;
        }).collect();
//        Caused by: java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Long

        //计算出这张表数据的 笛卡尔积
        log.info("计算出这张表数据的 笛卡尔积");
        JavaPairRDD<Row, Row> cartesian = friendInfoRdd.cartesian(friendInfoRdd);

        int ageCol=3;
        int sexCol=5;
        int cityCol=6;
        // 计算用户的相似度
        log.info(" 计算用户的相似度");
        JavaPairRDD<Long, Rating> javaPairRDD = cartesian.mapToPair(row -> {
            Row row1 = row._1();
            Row row2 = row._2();
//            idColNum
//            Long friendId1 = row1.getLong(1);
//            Long friendId2 = row2.getLong(1);
            Long friendId1 = row1.getLong(idColNum);
            Long friendId2 = row2.getLong(idColNum);
            Long key = friendId1 + friendId2 + RandomUtils.nextLong();

            // 自己与自己对比
            if (friendId1.longValue() == friendId2.longValue()) {
                return new Tuple2<>(key % 10, new Rating(friendId1.intValue(),
                        friendId2.intValue(), 0));
            }

            double similarity = 0;

            //计算年龄差
//            int ageDiff = Math.abs(row1.getInt(4) - row2.getInt(4));
            int ageDiff = Math.abs(row1.getInt(ageCol) - row2.getInt(ageCol));
            if (ageDiff <= 2) {
//                年龄小的相近 要推荐
                similarity += 70;
            } else if (ageDiff >= 3 && ageDiff <= 5) {
                similarity += 60;
            } else if (ageDiff > 5 && ageDiff <= 10) {
                similarity += 50;
            }

            // 计算性别
//            if (row1.getInt(6) != row2.getInt(6)) {
            if (row1.getInt(sexCol) != row2.getInt(sexCol)) {
                similarity += 10;
            }

            // 计算城市
//            String city1 = StringUtils.substringBefore(row1.getString(7), "-");
//            String city2 = StringUtils.substringBefore(row2.getString(7), "-");
            String city1 = StringUtils.substringBefore(row1.getString(cityCol), "-");
            String city2 = StringUtils.substringBefore(row2.getString(cityCol), "-");
            if (StringUtils.equals(city1, city2)) {
                similarity += 20;
            }

           Rating rating = new Rating(friendId1.intValue(), friendId2.intValue(), similarity);
            return new Tuple2<>(key % 10, rating);

        });

        // MLlib进行计算最佳的推荐模型
        log.info("MLlib进行计算最佳的推荐模型");
        MLlibRecommend mLlibRecommend = new MLlibRecommend();
        MatrixFactorizationModel bestModel = mLlibRecommend.bestModel(javaPairRDD);

        // 将数据写入到MongoDB中
        log.info("将数据写入到MongoDB中");
        JavaSparkContext jsc = new JavaSparkContext(sparkSession.sparkContext());

        for (Long friendId : friendIds) {
//            推荐了 50个吗
            Rating[] ratings = bestModel.recommendProducts(friendId.intValue(), 50);

//            JavaRDD<Rating> parallelize = jsc.parallelize(Arrays.asList(ratings));
            JavaRDD<Document> documentJavaRDD = jsc.parallelize(Arrays.asList(ratings)).map(v1 -> {
                Document document = new Document();

                document.put("_id", ObjectId.get());
//                rating product
                document.put("start", v1.product());
                document.put("end", v1.user());
                //得分，保留2位小数
                double similarity = new BigDecimal(v1.rating())
                        .setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
                document.put("similarity", similarity);
                document.put("remark", "相似");

                return document;
            });

            MongoSpark.save(documentJavaRDD);

        }

    }
}
