/*
 Navicat Premium Data Transfer

 Source Server         : 外网windows-mysql
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 101.132.39.29:3306
 Source Schema         : pdt

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 09/09/2021 15:57:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pdt_resource
-- ----------------------------
DROP TABLE IF EXISTS `pdt_resource`;
CREATE TABLE `pdt_resource`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pdt_resource
-- ----------------------------
INSERT INTO `pdt_resource` VALUES ('1', '接口1', NULL);
INSERT INTO `pdt_resource` VALUES ('2', '接口2', NULL);
INSERT INTO `pdt_resource` VALUES ('3', '文件1', NULL);
INSERT INTO `pdt_resource` VALUES ('4', '文件2', NULL);

-- ----------------------------
-- Table structure for pdt_role
-- ----------------------------
DROP TABLE IF EXISTS `pdt_role`;
CREATE TABLE `pdt_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rolename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pdt_role
-- ----------------------------
INSERT INTO `pdt_role` VALUES ('1', 'admin', '管理员', NULL);
INSERT INTO `pdt_role` VALUES ('2', 'user1', '普通用户1', NULL);
INSERT INTO `pdt_role` VALUES ('3', 'user2', '普通用户2', NULL);
INSERT INTO `pdt_role` VALUES ('4', 'user3', '普通用户3', NULL);

-- ----------------------------
-- Table structure for pdt_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `pdt_role_resource`;
CREATE TABLE `pdt_role_resource`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `resource_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pdt_role_resource
-- ----------------------------
INSERT INTO `pdt_role_resource` VALUES ('1', '1', '1');
INSERT INTO `pdt_role_resource` VALUES ('2', '1', '2');
INSERT INTO `pdt_role_resource` VALUES ('3', '1', '3');
INSERT INTO `pdt_role_resource` VALUES ('4', '1', '4');
INSERT INTO `pdt_role_resource` VALUES ('5', '2', '2');
INSERT INTO `pdt_role_resource` VALUES ('6', '2', '4');
INSERT INTO `pdt_role_resource` VALUES ('7', '3', '1');
INSERT INTO `pdt_role_resource` VALUES ('8', '3', '3');
INSERT INTO `pdt_role_resource` VALUES ('9', '4', '3');

-- ----------------------------
-- Table structure for pdt_user
-- ----------------------------
DROP TABLE IF EXISTS `pdt_user`;
CREATE TABLE `pdt_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT NULL COMMENT '账号状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pdt_user
-- ----------------------------
INSERT INTO `pdt_user` VALUES ('1', 'Taokoo', '1234', '大师兄', 'taokoo@qq.com', '10086', 1);
INSERT INTO `pdt_user` VALUES ('2', 'user1', '1234', '用户1', 'user1@qq.com', '', 1);
INSERT INTO `pdt_user` VALUES ('3', 'user2', '1234', '用户2', 'user2@qq.com', NULL, 1);
INSERT INTO `pdt_user` VALUES ('4', 'user3', '1234', '用户3', 'user3@qq.com', NULL, 1);

-- ----------------------------
-- Table structure for pdt_user_role
-- ----------------------------
DROP TABLE IF EXISTS `pdt_user_role`;
CREATE TABLE `pdt_user_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pdt_user_role
-- ----------------------------
INSERT INTO `pdt_user_role` VALUES ('1', '1', '1');
INSERT INTO `pdt_user_role` VALUES ('2', '2', '2');
INSERT INTO `pdt_user_role` VALUES ('3', '3', '3');
INSERT INTO `pdt_user_role` VALUES ('4', '4', '4');

SET FOREIGN_KEY_CHECKS = 1;
