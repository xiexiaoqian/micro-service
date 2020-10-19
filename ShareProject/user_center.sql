/*
 Navicat Premium Data Transfer

 Source Server         : 巴旦木的远程
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 39.97.253.19:3306
 Source Schema         : user_center

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 19/10/2020 17:40:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bonus_event_log
-- ----------------------------
DROP TABLE IF EXISTS `bonus_event_log`;
CREATE TABLE `bonus_event_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `user_id` int(0) NULL DEFAULT NULL COMMENT 'user.id',
  `value` int(0) NULL DEFAULT NULL COMMENT '积分操作值',
  `event` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发生的事件',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_bonus_event_log_user1_idx`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '积分变更记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bonus_event_log
-- ----------------------------
INSERT INTO `bonus_event_log` VALUES (1, 2, 50, 'CONTRIBUTE', '2020-10-06 20:54:37', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (2, 2, 50, 'CONTRIBUTE', '2020-10-06 21:00:03', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (3, 2, 50, 'CONTRIBUTE', '2020-10-07 05:09:46', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (4, 2, 50, 'CONTRIBUTE', '2020-10-07 05:13:36', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (5, 1, 50, 'CONTRIBUTE', '2020-10-08 16:59:54', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (6, 1, 50, 'CONTRIBUTE', '2020-10-08 17:02:09', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (7, 1, 50, 'CONTRIBUTE', '2020-10-08 17:21:44', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (8, 1, 50, 'CONTRIBUTE', '2020-10-08 17:22:07', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (9, 1, 50, 'CONTRIBUTE', '2020-10-08 17:22:30', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (10, 1, 50, 'CONTRIBUTE', '2020-10-08 17:28:40', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (11, 1, 50, 'CONTRIBUTE', '2020-10-08 17:29:15', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (12, 1, 50, 'CONTRIBUTE', '2020-10-08 17:29:16', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (13, 1, 50, 'CONTRIBUTE', '2020-10-08 18:15:45', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (14, 1, 50, 'CONTRIBUTE', '2020-10-08 18:18:25', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (15, 1, 50, 'CONTRIBUTE', '2020-10-08 18:25:03', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (16, 1, 50, 'CONTRIBUTE', '2020-10-08 19:41:27', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (17, 1, 50, 'CONTRIBUTE', '2020-10-08 19:42:49', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (18, 1, -77, 'CONTRIBUTE', '2020-10-15 16:36:03', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (19, 9, -77, 'CONTRIBUTE', '2020-10-15 16:38:49', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (20, 9, -89, 'CONTRIBUTE', '2020-10-15 17:37:23', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (21, 9, -70, 'CONTRIBUTE', '2020-10-15 18:21:06', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (22, 9, -89, 'CONTRIBUTE', '2020-10-15 18:34:40', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (23, 9, -77, 'CONTRIBUTE', '2020-10-15 19:13:22', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (24, 9, -89, 'CONTRIBUTE', '2020-10-15 20:12:24', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (25, 9, -77, 'CONTRIBUTE', '2020-10-15 20:17:40', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (26, 9, -70, 'CONTRIBUTE', '2020-10-15 20:18:05', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (27, 9, -70, 'CONTRIBUTE', '2020-10-15 20:19:57', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (28, 9, -70, 'CONTRIBUTE', '2020-10-15 20:20:10', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (29, 9, -70, 'CONTRIBUTE', '2020-10-15 20:20:20', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (30, 9, -70, 'CONTRIBUTE', '2020-10-15 23:33:48', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (31, 9, -70, 'CONTRIBUTE', '2020-10-16 08:57:10', '投稿加分');
INSERT INTO `bonus_event_log` VALUES (32, 9, -70, 'EXCHANGE', '2020-10-17 20:05:57', '兑换资源');
INSERT INTO `bonus_event_log` VALUES (35, 9, -123, 'EXCHANGE', '2020-10-18 21:10:26', '兑换资源');
INSERT INTO `bonus_event_log` VALUES (36, 9, -60, 'EXCHANGE', '2020-10-18 21:13:08', '兑换资源');
INSERT INTO `bonus_event_log` VALUES (37, 9, -45, 'EXCHANGE', '2020-10-18 23:05:57', '兑换资源');
INSERT INTO `bonus_event_log` VALUES (38, 9, 20, 'SIGN_IN', '2020-10-18 23:28:17', '签到加分');
INSERT INTO `bonus_event_log` VALUES (39, 9, 20, 'SIGN_IN', '2020-10-19 01:41:10', '签到加分');
INSERT INTO `bonus_event_log` VALUES (40, 9, -29, 'EXCHANGE', '2020-10-19 01:50:54', '兑换资源');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `wx_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '微信id',
  `wx_nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '微信昵称',
  `roles` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像地址',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `bonus` int(0) NOT NULL DEFAULT 300 COMMENT '积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分享' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'IfinityX7', '陶然然', '管理员', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/mqxu.jpg', '2020-09-24 17:43:37', '2020-09-24 17:43:40', 443);
INSERT INTO `user` VALUES (2, 'zhangmm', '张萌萌', '普通用户', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/1.jpg', '2020-09-24 17:45:20', '2020-09-24 17:45:23', 400);
INSERT INTO `user` VALUES (3, 'lizm', '李子木', '普通用户', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/2.jpg', '2020-10-01 17:15:44', '2020-10-01 17:15:49', 300);
INSERT INTO `user` VALUES (4, 'wxid1', '巴旦木', 'user', 'https://miaoxun.oss-cn-hangzhou.aliyuncs.com/photos/0fbf322f-f660-46d4-a8f9-9ea087abec4c.jpg', '2020-10-12 17:38:05', '2020-10-12 17:38:05', 100);
INSERT INTO `user` VALUES (9, 'ox0tP5S12Q2tVtGp82fBvfbbejic', '喵ฅ', 'admin', 'https://thirdwx.qlogo.cn/mmopen/vi_32/VO56uDVUJapsGCwRKl2ySJ2URq3d7CxyTsxoF0V5oibKibjGgg6JO3HuFdMukSF5RC8s3dWEYyIscpyB2RdgiaPfQ/132', '2020-10-14 11:18:31', '2020-10-14 11:18:31', 3283);

SET FOREIGN_KEY_CHECKS = 1;
