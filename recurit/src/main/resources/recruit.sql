/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : recruit

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 25/02/2022 19:06:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tab_category
-- ----------------------------
DROP TABLE IF EXISTS `tab_category`;
CREATE TABLE `tab_category`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`cid`) USING BTREE,
  UNIQUE INDEX `cname`(`cname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tab_category
-- ----------------------------
INSERT INTO `tab_category` VALUES (2, 'IT互联网');
INSERT INTO `tab_category` VALUES (1, '化工');
INSERT INTO `tab_category` VALUES (6, '实习');
INSERT INTO `tab_category` VALUES (4, '教育');
INSERT INTO `tab_category` VALUES (5, '选调/考公');
INSERT INTO `tab_category` VALUES (3, '金融');

-- ----------------------------
-- Table structure for tab_favorite
-- ----------------------------
DROP TABLE IF EXISTS `tab_favorite`;
CREATE TABLE `tab_favorite`  (
  `iid` int(11) NOT NULL,
  `createDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`iid`, `uid`) USING BTREE,
  INDEX `FK_user_favorite`(`uid`) USING BTREE,
  CONSTRAINT `FK_user_favorite` FOREIGN KEY (`uid`) REFERENCES `tab_user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_info_favorite` FOREIGN KEY (`iid`) REFERENCES `tab_info_list` (`iid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tab_favorite
-- ----------------------------
INSERT INTO `tab_favorite` VALUES (1, '2022-02-12 20:49:51', 1);
INSERT INTO `tab_favorite` VALUES (1, '2022-02-12 20:56:09', 13);

-- ----------------------------
-- Table structure for tab_info_list
-- ----------------------------
DROP TABLE IF EXISTS `tab_info_list`;
CREATE TABLE `tab_info_list`  (
  `iid` int(11) NOT NULL AUTO_INCREMENT,
  `iname` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `salary` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `detail` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `iflag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deadline` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cid` int(11) NOT NULL,
  `sourceId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`iid`) USING BTREE,
  UNIQUE INDEX `sourceId`(`sourceId`) USING BTREE,
  INDEX `FK_category_route`(`cid`) USING BTREE,
  CONSTRAINT `FK_category_route` FOREIGN KEY (`cid`) REFERENCES `tab_category` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 458 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tab_info_list
-- ----------------------------
INSERT INTO `tab_info_list` VALUES (1, '研发工程师', '10k/月', '化工研究员。在工程领域中，大部分化学工程师从事于化工产业，主要工作内容为化工产品制造程序的研究与开发，以及厂房与厂内设备之设计、建造、操作与维修。 \r\n一般来说，化学工程师运用化学工程理论，将进入工厂的各种原物料转换成各式商业化产品，举凡如食品、饮料、药品、化学品、石化产品、金属以及其他各式合成材料等，范围广泛，主要可分为制程、研发两大部分： ', 'Y', '2022.3.3', '2022-02-17 20:37:44', 1, '1');
INSERT INTO `tab_info_list` VALUES (2, '研发工程师', '10k/月', '化工研究员。在工程领域中，大部分化学工程师从事于化工产业，主要工作内容为化工产品制造程序的研究与开发，以及厂房与厂内设备之设计、建造、操作与维修。 \r\n一般来说，化学工程师运用化学工程理论，将进入工厂的各种原物料转换成各式商业化产品，举凡如食品、饮料、药品、化学品、石化产品、金属以及其他各式合成材料等，范围广泛，主要可分为制程、研发两大部分： ', 'N', '2022.3.3', '2022-02-17 20:37:50', 1, '2');
INSERT INTO `tab_info_list` VALUES (3, '3研发工程师\r\n研发工程师', '1', '化工研究员。在工程领域中，大部分化学工程师从事于化工产业，主要工作内容为化工产品制造程序的研究与开发，以及厂房与厂内设备之设计、建造、操作与维修。 \r\n一般来说，化学工程师运用化学工程理论，将进入工厂的各种原物料转换成各式商业化产品，举凡如食品、饮料、药品、化学品、石化产品、金属以及其他各式合成材料等，范围广泛，主要可分为制程、研发两大部分： ', 'Y', '2022.3.3', '2022-02-17 20:37:52', 1, '3');

-- ----------------------------
-- Table structure for tab_user
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tab_user
-- ----------------------------
INSERT INTO `tab_user` VALUES (1, '1', '1', '1', '1', '1');
INSERT INTO `tab_user` VALUES (12, '14', '1', '11', '1', '2');
INSERT INTO `tab_user` VALUES (13, '12', '1', '1', '1', '13');
INSERT INTO `tab_user` VALUES (14, '34', '23', '1', '1', '4');
INSERT INTO `tab_user` VALUES (15, '21000601', 'sdfsdfsdfsdfs', '2313084083@qq.com', 'Y', '65aafb5698a54bed9dadca6d0c9b839f');
INSERT INTO `tab_user` VALUES (16, '10011704234', 'sdfsdfsdfsdfs', '2343223425@wser.com', 'N', 'c0c2bca38cbe4348bc6984ced203e221');
INSERT INTO `tab_user` VALUES (17, '202002626', 'sdfsdfsdfsdfs', '2313084083@qq.q', 'N', 'e1a2beb67c624246bf1d0df13fe05409');
INSERT INTO `tab_user` VALUES (18, '202002dsg', 'sdfsdfsdfsdfs', '2313084083@qq.qe', 'N', 'c4f35a1bae1041b2abd6c772f41d38e7');

SET FOREIGN_KEY_CHECKS = 1;
