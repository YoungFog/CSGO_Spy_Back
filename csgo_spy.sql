/*
 Navicat Premium Data Transfer

 Source Server         : YoungFog
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 121.5.146.129:3306
 Source Schema         : csgo_spy

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 10/08/2022 06:06:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for match_
-- ----------------------------
DROP TABLE IF EXISTS `match_`;
CREATE TABLE `match_`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '比赛编号',
  `status` int(0) NULL DEFAULT NULL COMMENT '比赛状态\r\n0未开始\r\n1开始\r\n2结束',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for name
-- ----------------------------
DROP TABLE IF EXISTS `name`;
CREATE TABLE `name`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for player
-- ----------------------------
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player`  (
  `key` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `match_id` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '比赛编号',
  `player_id` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '比赛内标识符，A1-A5 B1-B5',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `isSpy` tinyint(1) NOT NULL COMMENT '0不是卧底 1是卧底',
  `hasVoted` tinyint(1) NOT NULL COMMENT '0没投过 1投过',
  `voted_num` int(0) NOT NULL DEFAULT 0 COMMENT '被投的票数',
  PRIMARY KEY (`key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `match_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name_from` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name_to` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
