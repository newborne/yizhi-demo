/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.111.133_3306
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 192.168.111.133:3306
 Source Schema         : yizhi

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 23/07/2021 19:25:32
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for friend_info
-- ----------------------------
DROP TABLE IF EXISTS `friend_info`;
CREATE TABLE `friend_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sex` int(1) NULL DEFAULT 3 COMMENT '性别，1-男，2-女，3-未知',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '居住城市',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friend_info
-- ----------------------------
INSERT INTO `friend_info` VALUES (1, '17345660628', '邹知', 26, '河南省信阳市罗山县止知始路归翳形社区72号', 1, '信阳市');
INSERT INTO `friend_info` VALUES (2, '14589533442', '萧于', 25, '河南省三门峡市卢氏县意明有路设言社区79号', 2, '三门峡市');
INSERT INTO `friend_info` VALUES (3, '18443280035', '傅后学', 20, '河南省信阳市浉河区意德修路来消命留社区93号', 1, '信阳市');
INSERT INTO `friend_info` VALUES (4, '13576126061', '萧天之', 26, '河南省信阳市光山县后欲齐路之荣园往社区43号', 2, '信阳市');
INSERT INTO `friend_info` VALUES (5, '13037652760', '康修', 26, '河南省洛阳市吉利区身而后路为而问求社区76号', 1, '洛阳市');
INSERT INTO `friend_info` VALUES (6, '14765905634', '曹后定', 28, '河南省济源市坡头镇意先治路亦觉舟趣社区10号', 2, '济源市');
INSERT INTO `friend_info` VALUES (7, '13652802885', '叶其', 18, '河南省周口市项城市修治路与实亲社区86号', 1, '周口市');
INSERT INTO `friend_info` VALUES (8, '14978566974', '叶致者', 29, '河南省濮阳市濮阳县格诚而路请菊怀社区68号', 2, '濮阳市');
INSERT INTO `friend_info` VALUES (9, '17017431488', '薛矣', 23, '河南省安阳市林州市之安路以棹社区35号', 1, '安阳市');
INSERT INTO `friend_info` VALUES (10, '15365926127', '田知其', 17, '河南省鹤壁市山城区知身路以尽心社区57号', 2, '鹤壁市');

SET FOREIGN_KEY_CHECKS = 1;
