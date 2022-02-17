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

 Date: 12/02/2022 21:07:51
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tab_category
-- ----------------------------
INSERT INTO `tab_category` VALUES (1, '1');

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tab_favorite
-- ----------------------------
INSERT INTO `tab_favorite` VALUES (1, '2022-02-12 20:49:51', 1);
INSERT INTO `tab_favorite` VALUES (1, '2022-02-12 20:56:09', 13);
INSERT INTO `tab_favorite` VALUES (456, '2022-02-12 20:55:49', 13);

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
) ENGINE = InnoDB AUTO_INCREMENT = 457 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tab_info_list
-- ----------------------------
INSERT INTO `tab_info_list` VALUES (1, '1', '1', '1', '1', '1', '2022-02-12 20:48:25', 1, '1');
INSERT INTO `tab_info_list` VALUES (456, '1', '1', '1', '1', '1', '2022-02-12 20:55:14', 1, '2');

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tab_user
-- ----------------------------
INSERT INTO `tab_user` VALUES (1, '1', '1', '1', '1', '1');
INSERT INTO `tab_user` VALUES (12, '14', '1', '11', '1', '2');
INSERT INTO `tab_user` VALUES (13, '12', '1', '1', '1', '13');
INSERT INTO `tab_user` VALUES (14, '34', '23', '1', '1', '4');

SET FOREIGN_KEY_CHECKS = 1;
