/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : localhost:3306
 Source Schema         : lingotube

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 28/05/2024 09:28:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `admin_count` int(11) NULL DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role
-- ----------------------------

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码（加密存储）',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `gender` tinyint(1) NOT NULL COMMENT '性别：0->未知；1->男；2->女',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像，存储url',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人介绍',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE,
  UNIQUE INDEX `uk_email`(`email`) USING BTREE,
  UNIQUE INDEX `uk_phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_user
-- ----------------------------
INSERT INTO `ums_user` VALUES (1, 'moncoder', '$2a$10$e5QvM/5JbZshG3l.0E6XN.n8KP1nvFJPqCf6DyTzbVTzp3WhUludO', '卡卡罗特', 0, '2002-08-11', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', 'ljy200220192020@outlook.com', '18854293818', '湖南省郴州市永兴县', '立志成为高手的程序猿', 1, '2024-04-17 17:36:57', '2024-05-16 20:22:25');
INSERT INTO `ums_user` VALUES (2, 'caoxin', '$2a$10$KsHJuPpl0sdYEW.lsZUntOwnYJkWI8Xc2qea0N06urbrnXEIzuHSm', 'null_linger', 0, NULL, NULL, '2955282539@qq.com', NULL, NULL, NULL, 1, '2024-04-30 14:31:41', '2024-04-30 14:31:41');

-- ----------------------------
-- Table structure for ums_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_login_log`;
CREATE TABLE `ums_user_login_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录日志ID，主键，自增',
  `user_id` int(11) NOT NULL COMMENT '用户ID，外键，关联用户表',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户登录时的IP地址',
  `os` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户登录时使用的操作系统',
  `browser` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户登录时使用的浏览器',
  `description` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关于此次登录的额外信息或备注',
  `create_time` datetime(0) NOT NULL COMMENT '用户登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_user_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for ums_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_role_relation`;
CREATE TABLE `ums_user_role_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '身份id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_user_role_relation
-- ----------------------------

-- ----------------------------
-- Table structure for vms_category
-- ----------------------------
DROP TABLE IF EXISTS `vms_category`;
CREATE TABLE `vms_category`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `is_enable` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否有效（0：无效，1：有效）',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '视频分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_category
-- ----------------------------
INSERT INTO `vms_category` VALUES (1, '学习资源', '提供有关准备托业、托福、雅思和全民英语测试的提示和一般建议。', 1, '2024-04-26 11:46:25', '2024-04-26 16:16:43');
INSERT INTO `vms_category` VALUES (2, '音乐', '通过音乐视频和歌词帮助您完善英语。', 1, '2024-04-26 11:48:35', '2024-04-26 11:48:35');
INSERT INTO `vms_category` VALUES (3, '动画', '该频道收集了简单的动画，通过故事扩展您的词汇和短语。', 1, '2024-04-26 11:49:09', '2024-04-26 11:49:09');
INSERT INTO `vms_category` VALUES (4, '儿童', '从零开始培养您的英语技能，使用英语基础知识和童谣。', 1, '2024-04-26 11:52:53', '2024-04-26 11:52:53');
INSERT INTO `vms_category` VALUES (5, '科学技术', '为您提供自然科学和前沿技术的知识，以拓宽您的视野。', 1, '2024-04-26 11:51:49', '2024-05-03 15:01:29');
INSERT INTO `vms_category` VALUES (6, '经济金融', '为您提供必备的商务英语技能，并更新您最新的国际金融事务。', 1, '2024-04-26 11:45:07', '2024-05-03 15:01:30');
INSERT INTO `vms_category` VALUES (7, '艺术娱乐', '汇集了电影预告片、片段和访谈节目，对于喜欢追剧或热爱艺术的人来说，这绝对是学习英语的最佳渠道。', 1, '2024-04-26 11:46:48', '2024-05-03 15:01:31');
INSERT INTO `vms_category` VALUES (8, '休闲旅行', '为您准备日常英语对话，涵盖旅行、交通、购物等方面的话题。', 1, '2024-04-26 11:52:04', '2024-05-03 15:01:32');
INSERT INTO `vms_category` VALUES (9, '健康福祉', '涵盖了广泛的社会问题，培养您用英语思考和进行讨论的能力。', 1, '2024-04-26 11:52:26', '2024-05-03 15:01:34');
INSERT INTO `vms_category` VALUES (10, '新闻时事', '与CNN、BBC和NPR等媒体巨头一起，学习热门关键词和新闻英语。', 1, '2024-04-26 11:49:57', '2024-05-03 15:01:36');

-- ----------------------------
-- Table structure for vms_home_latest_video
-- ----------------------------
DROP TABLE IF EXISTS `vms_home_latest_video`;
CREATE TABLE `vms_home_latest_video`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `video_id` int(11) UNSIGNED NOT NULL COMMENT '视频ID，外键，参考主视频表中的视频唯一标识',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频标题',
  `thumbnail_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '缩略图URL',
  `duration` int(11) UNSIGNED NOT NULL COMMENT '视频时长（秒）',
  `views` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '观看次数',
  `level_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频等级',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否上架首页（0：下架，1：上架）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '最新视频表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_home_latest_video
-- ----------------------------
INSERT INTO `vms_home_latest_video` VALUES (1, 6, 'MVP是谁？最佳防守球员谁？最快进步球员？各大奖项全预测！', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222637376.jpg', 600, 65536, 'A1', 0);
INSERT INTO `vms_home_latest_video` VALUES (2, 5, '【伯爵】元清治下，蒙古人何以经历700年浩劫？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222546410.jpg', 2500, 23405, 'A1', 0);
INSERT INTO `vms_home_latest_video` VALUES (3, 4, '电影字幕是中国特产？国外只有残疾人用？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222451332.jpg', 1500, 32101, 'A1', 0);
INSERT INTO `vms_home_latest_video` VALUES (4, 3, '吃 货 整 顿 职 场', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222259068.jpg', 2500, 205230, 'A1', 0);
INSERT INTO `vms_home_latest_video` VALUES (5, 2, '石勒是如何从奴隶成为开国皇帝的？【神奇皇帝】', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222047709.jpg', 1700, 25000, 'A1', 0);
INSERT INTO `vms_home_latest_video` VALUES (6, 1, '又一国产大厂手游，开服差点凉！究竟干了什么事？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417221845354.jpg', 348, 12101, 'A1', 0);
INSERT INTO `vms_home_latest_video` VALUES (7, 58, '日元创下34年新低对日本公司、消费者和旅游业的意义', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504152859929.jpg', 255, 0, 'C2', 0);
INSERT INTO `vms_home_latest_video` VALUES (8, 57, '习惯的力量', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504152457859.jpg', 175, 0, 'B2', 0);
INSERT INTO `vms_home_latest_video` VALUES (9, 56, '完美主义者的陷阱', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504152400627.jpg', 227, 0, 'C1', 0);
INSERT INTO `vms_home_latest_video` VALUES (10, 55, '我如何在2023年进行自我约束', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504152303259.jpg', 255, 0, 'B2', 0);
INSERT INTO `vms_home_latest_video` VALUES (11, 54, '为什么日本有这么多自动贩卖机', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504151942473.jpg', 286, 0, 'C1', 0);
INSERT INTO `vms_home_latest_video` VALUES (12, 53, '如何与陌生人交谈', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504151823270.jpg', 210, 0, 'A2', 0);
INSERT INTO `vms_home_latest_video` VALUES (13, 58, '日元创下34年新低对日本公司、消费者和旅游业的意义', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504152859929.jpg', 255, 0, 'C2', 1);
INSERT INTO `vms_home_latest_video` VALUES (14, 57, '习惯的力量', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504152457859.jpg', 175, 0, 'B2', 1);
INSERT INTO `vms_home_latest_video` VALUES (15, 56, '完美主义者的陷阱', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504152400627.jpg', 227, 0, 'C1', 1);
INSERT INTO `vms_home_latest_video` VALUES (16, 55, '我如何在2023年进行自我约束', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504152303259.jpg', 255, 0, 'B2', 1);
INSERT INTO `vms_home_latest_video` VALUES (17, 54, '为什么日本有这么多自动贩卖机', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504151942473.jpg', 286, 0, 'C1', 1);
INSERT INTO `vms_home_latest_video` VALUES (18, 53, '如何与陌生人交谈', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504151823270.jpg', 210, 0, 'A2', 1);

-- ----------------------------
-- Table structure for vms_home_recommended_video
-- ----------------------------
DROP TABLE IF EXISTS `vms_home_recommended_video`;
CREATE TABLE `vms_home_recommended_video`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `video_id` int(11) UNSIGNED NOT NULL COMMENT '视频ID，外键，参考主视频表中的视频唯一标识',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频标题',
  `thumbnail_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '缩略图URL',
  `duration` int(11) UNSIGNED NOT NULL COMMENT '视频时长（秒）',
  `views` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '观看次数',
  `level_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频等级',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否上架首页（0：下架，1：上架）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '推荐视频表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_home_recommended_video
-- ----------------------------
INSERT INTO `vms_home_recommended_video` VALUES (1, 3, '吃 货 整 顿 职 场', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222259068.jpg', 2500, 205230, 'A1', 0);
INSERT INTO `vms_home_recommended_video` VALUES (2, 4, '电影字幕是中国特产？国外只有残疾人用？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222451332.jpg', 1500, 32101, 'A1', 0);
INSERT INTO `vms_home_recommended_video` VALUES (3, 5, '【伯爵】元清治下，蒙古人何以经历700年浩劫？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222546410.jpg', 2500, 23405, 'A1', 0);
INSERT INTO `vms_home_recommended_video` VALUES (4, 6, 'MVP是谁？最佳防守球员谁？最快进步球员？各大奖项全预测！', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222637376.jpg', 600, 65536, 'A1', 0);
INSERT INTO `vms_home_recommended_video` VALUES (5, 2, '石勒是如何从奴隶成为开国皇帝的？【神奇皇帝】', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222047709.jpg', 1700, 25000, 'A1', 0);
INSERT INTO `vms_home_recommended_video` VALUES (6, 1, '又一国产大厂手游，开服差点凉！究竟干了什么事？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417221845354.jpg', 348, 12101, 'A1', 0);
INSERT INTO `vms_home_recommended_video` VALUES (7, 1, '又一国产大厂手游，开服差点凉！究竟干了什么事？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417221845354.jpg', 348, 0, 'A1', 0);
INSERT INTO `vms_home_recommended_video` VALUES (8, 2, '石勒是如何从奴隶成为开国皇帝的？【神奇皇帝】', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222047709.jpg', 1700, 0, 'A1', 0);
INSERT INTO `vms_home_recommended_video` VALUES (9, 3, '吃 货 整 顿 职 场', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222451332.jpg', 50, 0, 'A1', 0);
INSERT INTO `vms_home_recommended_video` VALUES (10, 4, '电影字幕是中国特产？国外只有残疾人用？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222259068.jpg', 1500, 0, 'A1', 0);
INSERT INTO `vms_home_recommended_video` VALUES (11, 5, '【伯爵】元清治下，蒙古人何以经历700年浩劫？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222546410.jpg', 2500, 0, 'A1', 0);
INSERT INTO `vms_home_recommended_video` VALUES (12, 6, 'MVP是谁？最佳防守球员谁？最快进步球员？各大奖项全预测！', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222637376.jpg', 600, 0, 'A1', 0);
INSERT INTO `vms_home_recommended_video` VALUES (13, 11, '如何学习英语 - 你打算做什么？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429165657603.jpg', 92, 212452, 'A1', 1);
INSERT INTO `vms_home_recommended_video` VALUES (14, 12, '在美丽的春日里学习英语吧！🍃🌱🐣', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429170327338.jpg', 459, 4242, 'A1', 1);
INSERT INTO `vms_home_recommended_video` VALUES (15, 10, 'BBC新闻回顾：最热的3月', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429165331111.jpg', 340, 2424, 'B1', 1);
INSERT INTO `vms_home_recommended_video` VALUES (16, 15, '剧透警告：我们说的英语', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240430091955163.jpg', 150, 1314, 'B2', 1);
INSERT INTO `vms_home_recommended_video` VALUES (17, 16, '症结所在：我们说的英语', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240430092317236.jpg', 166, 321, 'B1', 1);
INSERT INTO `vms_home_recommended_video` VALUES (18, 14, '说英语 - 打扮起来 还是 穿上衣服', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240430091242852.jpg', 332, 1121, 'A2', 1);

-- ----------------------------
-- Table structure for vms_home_trending_video
-- ----------------------------
DROP TABLE IF EXISTS `vms_home_trending_video`;
CREATE TABLE `vms_home_trending_video`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `video_id` int(11) UNSIGNED NOT NULL COMMENT '视频ID，外键，参考主视频表中的视频唯一标识',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频标题',
  `thumbnail_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '缩略图URL',
  `duration` int(11) UNSIGNED NOT NULL COMMENT '视频时长（秒）',
  `views` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '观看次数',
  `level_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频等级',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否上架首页（0：下架，1：上架）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '最热视频表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_home_trending_video
-- ----------------------------
INSERT INTO `vms_home_trending_video` VALUES (1, 3, '吃 货 整 顿 职 场', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222259068.jpg', 2500, 205230, 'A1', 0);
INSERT INTO `vms_home_trending_video` VALUES (2, 6, 'MVP是谁？最佳防守球员谁？最快进步球员？各大奖项全预测！', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222637376.jpg', 600, 65536, 'A1', 0);
INSERT INTO `vms_home_trending_video` VALUES (3, 4, '电影字幕是中国特产？国外只有残疾人用？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222451332.jpg', 1500, 32101, 'A1', 0);
INSERT INTO `vms_home_trending_video` VALUES (4, 2, '石勒是如何从奴隶成为开国皇帝的？【神奇皇帝】', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222047709.jpg', 1700, 25000, 'A1', 0);
INSERT INTO `vms_home_trending_video` VALUES (5, 5, '【伯爵】元清治下，蒙古人何以经历700年浩劫？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222546410.jpg', 2500, 23405, 'A1', 0);
INSERT INTO `vms_home_trending_video` VALUES (6, 1, '又一国产大厂手游，开服差点凉！究竟干了什么事？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417221845354.jpg', 348, 12101, 'A1', 0);
INSERT INTO `vms_home_trending_video` VALUES (7, 7, '工作但贫困：数百万人就业却生活在贫困中', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240426112710313.jpg', 395, 1, 'A2', 0);
INSERT INTO `vms_home_trending_video` VALUES (8, 8, '终身禁烟 - BBC新闻评论', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429094525149.jpg', 476, 1, 'B2', 0);
INSERT INTO `vms_home_trending_video` VALUES (9, 9, '英式英语与美式英语_英语发音课程', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429095005869.jpg', 402, 1, 'A2', 0);
INSERT INTO `vms_home_trending_video` VALUES (10, 1, '又一国产大厂手游，开服差点凉！究竟干了什么事？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417221845354.jpg', 348, 0, 'A1', 0);
INSERT INTO `vms_home_trending_video` VALUES (11, 2, '石勒是如何从奴隶成为开国皇帝的？【神奇皇帝】', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222047709.jpg', 1700, 0, 'A1', 0);
INSERT INTO `vms_home_trending_video` VALUES (12, 3, '吃 货 整 顿 职 场', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222451332.jpg', 50, 0, 'A1', 0);
INSERT INTO `vms_home_trending_video` VALUES (13, 12, '在美丽的春日里学习英语吧！🍃🌱🐣', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429170327338.jpg', 459, 32, 'A1', 0);
INSERT INTO `vms_home_trending_video` VALUES (14, 11, '如何学习英语 - 你打算做什么？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429165657603.jpg', 92, 21, 'A1', 0);
INSERT INTO `vms_home_trending_video` VALUES (15, 10, 'BBC新闻回顾：最热的3月', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429165331111.jpg', 340, 12, 'B1', 0);
INSERT INTO `vms_home_trending_video` VALUES (16, 7, '工作但贫困：数百万人就业却生活在贫困中', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240426112710313.jpg', 395, 1, 'A2', 0);
INSERT INTO `vms_home_trending_video` VALUES (17, 8, '终身禁烟 - BBC新闻评论', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429094525149.jpg', 476, 1, 'B2', 0);
INSERT INTO `vms_home_trending_video` VALUES (18, 9, '英式英语与美式英语_英语发音课程', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429095005869.jpg', 402, 1, 'A2', 0);
INSERT INTO `vms_home_trending_video` VALUES (19, 12, '在美丽的春日里学习英语吧！🍃🌱🐣', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429170327338.jpg', 459, 32, 'A1', 1);
INSERT INTO `vms_home_trending_video` VALUES (20, 11, '如何学习英语 - 你打算做什么？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429165657603.jpg', 92, 21, 'A1', 1);
INSERT INTO `vms_home_trending_video` VALUES (21, 10, 'BBC新闻回顾：最热的3月', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429165331111.jpg', 340, 12, 'B1', 1);
INSERT INTO `vms_home_trending_video` VALUES (22, 7, '工作但贫困：数百万人就业却生活在贫困中', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240426112710313.jpg', 395, 1, 'A2', 1);
INSERT INTO `vms_home_trending_video` VALUES (23, 8, '终身禁烟 - BBC新闻评论', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429094525149.jpg', 476, 1, 'B2', 1);
INSERT INTO `vms_home_trending_video` VALUES (24, 9, '英式英语与美式英语_英语发音课程', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429095005869.jpg', 402, 1, 'A2', 1);

-- ----------------------------
-- Table structure for vms_level
-- ----------------------------
DROP TABLE IF EXISTS `vms_level`;
CREATE TABLE `vms_level`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '等级唯一标识ID\r\n',
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '等级名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '等级描述',
  `is_enable` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否有效，0：无效，1：有效',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '视频等级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_level
-- ----------------------------
INSERT INTO `vms_level` VALUES (1, 'A1', '能够理解和运用熟悉的日常表达和非常基础的短语，以满足具体类型的需求。能够介绍自己和他人，并询问和回答关于个人细节的问题，比如他/她住在哪里，认识的人，以及拥有的东西。可以以简单的方式进行交流，前提是对方说话缓慢清晰，并愿意提供帮助。', 1, '2024-04-14 11:27:19', '2024-04-26 10:44:58');
INSERT INTO `vms_level` VALUES (2, 'A2', '能够理解与最紧急相关领域相关的句子和常用表达（例如，非常基本的个人和家庭信息、购物、当地地理、就业等）。能够在需要进行简单和日常任务的情况下进行简单和直接的信息交流。能够简单地描述自己背景、周围环境以及紧急需求领域的事项。', 1, '2024-04-14 11:27:41', '2024-04-14 11:28:31');
INSERT INTO `vms_level` VALUES (3, 'B1', '能够理解在工作、学校、休闲等方面经常遇到的熟悉事务的主要要点。能够处理在语言使用地区旅行时可能出现的大多数情况。能够就熟悉或个人感兴趣的主题产生简单的连贯文本。能够描述经历、事件、梦想、希望和抱负，并简要说明观点和计划的原因和解释。', 1, '2024-04-14 11:28:38', '2024-04-14 11:28:44');
INSERT INTO `vms_level` VALUES (4, 'B2', '能够理解关于具体和抽象主题的复杂文本的主要思想，包括其专业领域的技术讨论。能够以一定的流畅度和自然度进行互动，使与母语为此语言的人士的正常交流成为可能，对双方都不会有压力。能够就广泛的主题产生清晰、详细的文本，并解释对某一时事问题的观点，说明各种选项的优缺点。', 1, '2024-04-14 11:29:09', '2024-04-14 11:29:12');
INSERT INTO `vms_level` VALUES (5, 'C1', '能够理解广泛而要求较高、较长的文本，并识别其中的含蓄意义。能够流利、自然地表达自己，不需要显然地寻找表达方式。能够灵活有效地运用语言进行社交、学术和专业用途。能够就复杂的主题产生清晰、结构良好、详细的文本，展现对组织模式、连接词和凝聚性设备的控制使用。', 1, '2024-04-14 11:28:38', '2024-04-14 11:28:44');
INSERT INTO `vms_level` VALUES (6, 'C2', '能够轻松理解听到或阅读到的几乎所有内容。能够从不同的口头和书面来源总结信息，重构论点和叙述，以连贯的方式呈现。能够自发、非常流利和准确地表达自己，即使在更复杂的情况下，也能区分更细微的意义差异。', 1, '2024-04-14 11:29:09', '2024-04-14 11:29:12');

-- ----------------------------
-- Table structure for vms_movie
-- ----------------------------
DROP TABLE IF EXISTS `vms_movie`;
CREATE TABLE `vms_movie`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '电影唯一标识ID',
  `video_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT 'video表ID',
  `director` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '导演',
  `actors` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主演列表',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `region_id` int(11) UNSIGNED NOT NULL COMMENT 'vms_region表ID',
  `category_id` int(11) UNSIGNED NOT NULL COMMENT 'vms_category表ID',
  `score` decimal(3, 1) NULL DEFAULT NULL COMMENT '评分',
  `duration` int(11) NULL DEFAULT NULL COMMENT '电影时长（分钟）',
  `views_count` int(11) NULL DEFAULT 0 COMMENT '观看次数',
  `release_year` int(4) NULL DEFAULT NULL COMMENT '上映年份',
  `release_datetime` datetime(0) NULL DEFAULT NULL COMMENT '上映时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_video_id`(`video_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '电影表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_movie
-- ----------------------------

-- ----------------------------
-- Table structure for vms_region
-- ----------------------------
DROP TABLE IF EXISTS `vms_region`;
CREATE TABLE `vms_region`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地区ID，主键，自增',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地区名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地区描述（可选）',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '地区表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_region
-- ----------------------------

-- ----------------------------
-- Table structure for vms_series
-- ----------------------------
DROP TABLE IF EXISTS `vms_series`;
CREATE TABLE `vms_series`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '电视剧唯一标识ID',
  `director` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '导演',
  `actors` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主演列表',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `region_id` int(11) NOT NULL COMMENT '地区表ID',
  `category_id` int(11) NOT NULL COMMENT '分类表ID',
  `score` decimal(3, 1) NULL DEFAULT NULL COMMENT '评分',
  `total_episodes` int(11) NOT NULL COMMENT '总集数',
  `views_count` int(11) NULL DEFAULT 0 COMMENT '观看次数',
  `release_year` int(4) NULL DEFAULT NULL COMMENT '上映年份',
  `release_time` datetime(0) NULL DEFAULT NULL COMMENT '上映时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '电视剧表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_series
-- ----------------------------

-- ----------------------------
-- Table structure for vms_series_video_relation
-- ----------------------------
DROP TABLE IF EXISTS `vms_series_video_relation`;
CREATE TABLE `vms_series_video_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `series_id` int(11) NOT NULL COMMENT '电视剧表ID',
  `video_id` int(11) NOT NULL COMMENT '视频表ID',
  `episode` int(11) NOT NULL COMMENT '电视剧的第几集',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '电视剧与视频集数关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_series_video_relation
-- ----------------------------

-- ----------------------------
-- Table structure for vms_user_favorite_folder
-- ----------------------------
DROP TABLE IF EXISTS `vms_user_favorite_folder`;
CREATE TABLE `vms_user_favorite_folder`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '收藏夹ID，主键，自增',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '用户ID，外键，关联用户表',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收藏夹名称',
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面图片URL或路径',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为默认收藏夹，1为默认，0为非默认',
  `is_public` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为公开收藏夹，1为公开，0为私有',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户收藏夹表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_user_favorite_folder
-- ----------------------------
INSERT INTO `vms_user_favorite_folder` VALUES (1, 1, '默认收藏夹', 'uploads/2024-03-28T133445.7141651+0800cicely_Semi-realistic_Anime_Style_by_Martine_Johanna__Equirecta_9e4caecf-dc69-4029-a0fb-028de860d678.jpg', '', 1, 1, '2024-03-28 13:40:07', '2024-03-28 13:40:07');
INSERT INTO `vms_user_favorite_folder` VALUES (3, 1, '收藏夹', '', '', 0, 0, '2024-03-28 13:54:12', '2024-03-28 14:16:47');
INSERT INTO `vms_user_favorite_folder` VALUES (4, 1, '收藏夹', '', '', 0, 0, '2024-03-28 14:16:30', '2024-03-28 14:16:30');

-- ----------------------------
-- Table structure for vms_user_favorite_folder_video
-- ----------------------------
DROP TABLE IF EXISTS `vms_user_favorite_folder_video`;
CREATE TABLE `vms_user_favorite_folder_video`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '收藏记录ID，主键，自增',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '用户ID，外键，关联用户表',
  `video_id` int(11) UNSIGNED NOT NULL COMMENT '视频ID，外键，关联视频表',
  `folder_id` int(11) UNSIGNED NOT NULL COMMENT '收藏夹ID，外键，关联收藏夹表',
  `favorite_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户收藏视频记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_user_favorite_folder_video
-- ----------------------------
INSERT INTO `vms_user_favorite_folder_video` VALUES (1, 1, 1, 3, '2024-03-30 16:54:55');
INSERT INTO `vms_user_favorite_folder_video` VALUES (2, 1, 1, 1, '2024-03-30 17:25:17');
INSERT INTO `vms_user_favorite_folder_video` VALUES (3, 1, 1, 4, '2024-03-30 17:25:17');
INSERT INTO `vms_user_favorite_folder_video` VALUES (4, 1, 2, 1, '2024-04-03 12:20:25');
INSERT INTO `vms_user_favorite_folder_video` VALUES (10, 1, 3, 3, '2024-04-03 12:22:23');

-- ----------------------------
-- Table structure for vms_video
-- ----------------------------
DROP TABLE IF EXISTS `vms_video`;
CREATE TABLE `vms_video`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '视频唯一标识',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频标题',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频描述',
  `video_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频url\r\n',
  `thumbnail_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '封面url',
  `is_enable` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '视频是否下架（0：下架，1：上架）',
  `duration` int(11) UNSIGNED NOT NULL COMMENT '视频时长（秒）',
  `views` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '观看次数',
  `likes` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '点赞次数',
  `dislikes` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '点踩次数',
  `favorites` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '收藏次数',
  `shares` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '分享次数',
  `comments` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '评论次数',
  `uploader_id` int(11) UNSIGNED NOT NULL COMMENT '上传者id，外键，关联用户表',
  `uploader_nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传者昵称',
  `uploader_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传者头像，存储url',
  `upload_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '上传时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `level_id` int(11) UNSIGNED NOT NULL COMMENT '视频等级id，外键，关联等级表',
  `level_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '等级名称',
  `category_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '视频分类id，外键，关联分类表',
  `category_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '视频类型（0：普通视频，1：电视剧，2：电影）',
  `episode` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '剧集的第几集',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '视频表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_video
-- ----------------------------
INSERT INTO `vms_video` VALUES (1, '又一国产大厂手游，开服差点凉！究竟干了什么事？', '又一国产大厂手游，开服差点凉！究竟干了什么事？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240417221844482.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417221845354.jpg', 1, 348, 2, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-14 16:08:01', '2024-05-06 22:25:34', 1, 'A1', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (2, '石勒是如何从奴隶成为开国皇帝的？【神奇皇帝】', '石勒是如何从奴隶成为开国皇帝的？【神奇皇帝】', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240417222046506.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222047709.jpg', 1, 1700, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-15 16:05:26', '2024-05-04 16:54:49', 1, 'A1', 2, '音乐', 0, 0);
INSERT INTO `vms_video` VALUES (3, '吃 货 整 顿 职 场', '吃 货 整 顿 职 场', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240417222255528.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222451332.jpg', 1, 50, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-15 16:08:31', '2024-05-04 16:54:48', 1, 'A1', 3, '动画', 0, 0);
INSERT INTO `vms_video` VALUES (4, '电影字幕是中国特产？国外只有残疾人用？', '电影字幕是中国特产？国外只有残疾人用？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240417222448945.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222259068.jpg', 1, 1500, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-15 16:09:59', '2024-05-04 16:54:52', 1, 'A1', 4, '儿童', 0, 0);
INSERT INTO `vms_video` VALUES (5, '【伯爵】元清治下，蒙古人何以经历700年浩劫？', '【伯爵】元清治下，蒙古人何以经历700年浩劫？', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240417222546177.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222546410.jpg', 1, 2500, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-15 16:10:57', '2024-05-04 16:54:53', 1, 'A1', 5, '科学与技术', 0, 0);
INSERT INTO `vms_video` VALUES (6, 'MVP是谁？最佳防守球员谁？最快进步球员？各大奖项全预测！', 'MVP是谁？最佳防守球员谁？最快进步球员？各大奖项全预测！', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240417222636477.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240417222637376.jpg', 1, 600, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-16 15:48:07', '2024-05-04 16:54:55', 1, 'A1', 7, '艺术与娱乐', 0, 0);
INSERT INTO `vms_video` VALUES (7, '工作但贫困：数百万人就业却生活在贫困中', 'Working but poor: millions in work and in poverty', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240426112329682.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240426112710313.jpg', 1, 395, 1, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-26 11:31:57', '2024-04-29 13:47:18', 2, 'A2', 6, '经济与金融', 0, 0);
INSERT INTO `vms_video` VALUES (8, '终身禁烟 - BBC新闻评论', 'Banning smoking for life_ BBC News Review', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240429094431447.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429094525149.jpg', 1, 476, 2, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-29 09:49:30', '2024-05-06 20:43:38', 4, 'B2', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (9, '英式英语与美式英语_英语发音课程', 'British vs American _ English Pronunciation Lesson', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240429094946787.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429095005869.jpg', 1, 402, 1, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-29 09:52:51', '2024-04-29 13:48:07', 2, 'A2', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (10, 'BBC新闻回顾：最热的3月', 'Hottest March ever_ BBC News Review', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240429165319748.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429165331111.jpg', 1, 340, 2424, 2002, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-29 16:53:45', '2024-05-06 22:35:19', 3, 'B1', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (11, '如何学习英语 - 你打算做什么？', 'How to Learn English_ _What are you going to do', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240429165644365.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429165657603.jpg', 1, 92, 212452, 2424, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-29 16:57:00', '2024-05-04 17:03:21', 1, 'A1', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (12, '在美丽的春日里学习英语吧！🍃🌱🐣', 'Learn English on a Beautiful Spring Day! 🍃🌱🐣', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240429170228287.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240429170327338.jpg', 1, 459, 4242, 2325, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-29 17:03:30', '2024-05-04 17:03:24', 1, 'A1', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (13, '和我用英语对话_5分钟口语练习_提高你的口语技巧', 'Speak English with me _ 5 min Speaking Practice _ Improve Your Speaking Skills', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240430090802685.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240430090758318.jpg', 1, 254, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-30 09:08:16', '2024-04-30 09:08:16', 3, 'B1', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (14, '说英语 - 打扮起来 还是 穿上衣服', 'Speaking English - Dress up or Get dressed', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240430091246850.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240430091242852.jpg', 1, 332, 1121, 21, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-30 09:12:55', '2024-05-04 17:02:44', 2, 'A2', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (15, '剧透警告：我们说的英语', 'Spoiler alert_ The English We Speak', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240430091957347.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240430091955163.jpg', 1, 150, 1314, 32, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-30 09:19:59', '2024-05-04 17:02:51', 4, 'B2', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (16, '症结所在：我们说的英语', 'Sticking point_ The English We Speak', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240430092318713.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240430092317236.jpg', 1, 166, 321, 26, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-30 09:23:21', '2024-05-04 17:02:58', 3, 'B1', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (17, '别紧张，放松点！常见英语搭配', 'TAKE it Easy! Common English Collocations', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240430093133920.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240430093130000.jpg', 1, 438, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-30 09:31:41', '2024-04-30 09:31:41', 2, 'A2', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (18, '英语词汇的秘诀——如何记住更多', 'The Secret to English Vocabulary – How to remember more', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240430093435729.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240430093432710.jpg', 1, 521, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-30 09:34:52', '2024-04-30 09:34:52', 2, 'A2', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (19, '提升你的英语水平——我们说的英语', 'Up your game_ The English We Speak', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240430093900861.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240430093858381.jpg', 1, 153, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-30 09:39:03', '2024-04-30 09:40:08', 3, 'B1', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (20, '极简主义的意义是什么', 'What does minimalism really mean', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240430100125980.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240430100122924.jpg', 1, 158, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-30 10:01:42', '2024-04-30 10:01:42', 6, 'C2', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (21, '及物动词与不及物动词_ 自然英语语法解析', 'Transitive and Intransitive Verbs _ Natural English Grammar', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240430100600624.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240430100558417.jpg', 1, 308, 2, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-30 10:06:15', '2024-05-13 09:32:23', 5, 'C1', 1, '学习资源', 0, 0);
INSERT INTO `vms_video` VALUES (22, '团结人们以促成变革_ 安德鲁·派克斯', 'Bringing people together for change_ Andrew Pakes', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503140428920.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503140426541.jpg', 1, 115, 0, 1, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:04:34', '2024-05-06 22:36:34', 4, 'B2', 6, '经济与金融', 0, 0);
INSERT INTO `vms_video` VALUES (23, '如何进行闲聊', 'How To Make Small Talk', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503140919864.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503140920791.jpg', 1, 122, 1, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:09:21', '2024-05-13 09:49:03', 3, 'B1', 6, '经济与金融', 0, 0);
INSERT INTO `vms_video` VALUES (24, '人们为什么要工作', 'Why do people work', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503141126534.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503141128076.jpg', 1, 119, 2, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:11:28', '2024-05-13 09:45:28', 1, 'A1', 6, '经济与金融', 0, 0);
INSERT INTO `vms_video` VALUES (25, '英语面试_英语学习技巧_剑桥英语', 'Job Interviews in English _ English Language Learning Tips _ Cambridge English', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503141830917.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503141831871.jpg', 1, 124, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:18:32', '2024-05-03 14:22:15', 6, 'C2', 6, '经济与金融', 0, 0);
INSERT INTO `vms_video` VALUES (27, '十大面试问题及答案', 'Top 10 Job Interview Questions and Answers', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503142452729.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503142453965.jpg', 1, 212, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:24:54', '2024-05-03 14:24:54', 2, 'A2', 6, '经济与金融', 0, 0);
INSERT INTO `vms_video` VALUES (28, '5个比你小时候意识到的要黑暗得多的童话故事', '5 Fairy Tales That Were Way Darker Than You Realized as a Kid', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503143418778.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503143420888.jpg', 1, 221, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:34:21', '2024-05-03 14:34:21', 3, 'B1', 7, '艺术与娱乐', 0, 0);
INSERT INTO `vms_video` VALUES (29, '宫崎骏 ► 幸福', 'Hayao Miyazaki ► Happiness', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503143530440.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503143534719.jpg', 1, 178, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:35:35', '2024-05-03 14:35:35', 1, 'A1', 7, '艺术与娱乐', 0, 0);
INSERT INTO `vms_video` VALUES (30, '流行文化：我们为何以及在何时会在电影中哭泣', 'POP CULTURE_ When and Why We Cry In Films', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503143724477.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503143726690.jpg', 1, 220, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:37:27', '2024-05-03 14:37:27', 4, 'B2', 7, '艺术与娱乐', 0, 0);
INSERT INTO `vms_video` VALUES (31, '《人群中的某人》——《爱乐之城》（2016）', 'Someone In The Crowd - La La Land (2016)', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503143825334.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503143827741.jpg', 1, 270, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:38:27', '2024-05-03 14:38:27', 5, 'C1', 7, '艺术与娱乐', 0, 0);
INSERT INTO `vms_video` VALUES (32, '《神奇女侠1984》——官方预告片', 'Wonder Woman 1984 – Official Trailer', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503144002196.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503144003524.jpg', 1, 144, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:40:03', '2024-05-03 14:40:03', 1, 'A1', 7, '艺术与娱乐', 0, 0);
INSERT INTO `vms_video` VALUES (33, 'Spotify 上的《Beautiful Soul》', 'Beautiful Soul - Jesse McCartney (Boyce Avenue acoustic cover) on Spotify', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503144706482.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503144707826.jpg', 1, 240, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:47:08', '2024-05-03 14:47:08', 1, 'A1', 2, '音乐', 0, 0);
INSERT INTO `vms_video` VALUES (34, 'Clean Bandit - 《Tears》', 'Clean Bandit - Tears (feat. Louisa Johnson) [Official Video]', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503145015648.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503145017557.jpg', 1, 237, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:50:17', '2024-05-03 14:50:17', 2, 'A2', 2, '音乐', 0, 0);
INSERT INTO `vms_video` VALUES (35, 'Lukas Graham - 7 Years ', 'Lukas Graham - 7 Years [Official Music Video]', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503145126930.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503145128108.jpg', 1, 239, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:51:28', '2024-05-03 14:51:28', 3, 'B1', 2, '音乐', 0, 0);
INSERT INTO `vms_video` VALUES (36, 'See You Again', 'See You Again - Wiz Khalifa feat. Charlie Puth (Boyce Avenue feat. Bea Miller) on Spotify', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503145235649.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503145236829.jpg', 1, 262, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:52:37', '2024-05-03 14:52:37', 3, 'B1', 2, '音乐', 0, 0);
INSERT INTO `vms_video` VALUES (37, 'SEKAI NO OWARI ', 'SEKAI NO OWARI - Dragon Night', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503145335190.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503145338265.jpg', 1, 295, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:53:38', '2024-05-03 14:53:38', 2, 'A2', 2, '音乐', 0, 0);
INSERT INTO `vms_video` VALUES (38, '《玩具总动员4》中你错过的小细节', 'Small Details You Missed In Toy Story 4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503145738883.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503145743833.jpg', 1, 293, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:57:44', '2024-05-03 14:57:44', 2, 'A2', 3, '动画', 0, 0);
INSERT INTO `vms_video` VALUES (39, '《Wish》官方预告片', 'Wish _ Official Trailer', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503145840729.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503145844097.jpg', 1, 142, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:58:44', '2024-05-03 14:58:44', 1, 'A1', 3, '动画', 0, 0);
INSERT INTO `vms_video` VALUES (40, '你在星巴克排队时的思绪', 'Thoughts You Have In Line At Starbucks', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503145931554.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503145935616.jpg', 1, 85, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 14:59:35', '2024-05-03 14:59:35', 4, 'B2', 3, '动画', 0, 0);
INSERT INTO `vms_video` VALUES (41, '卡通剪纸风格动画解说视频_儿童编程教学', 'Cutout Cartoon Animated Explainer Video _ Coding For Kids', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503151941072.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503151942199.jpg', 1, 77, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 15:19:42', '2024-05-03 15:19:42', 1, 'A1', 4, '儿童', 0, 0);
INSERT INTO `vms_video` VALUES (42, '章鱼哥的恶作剧！🤡愚人节快乐！', 'Every Squidward PRANK Ever! 🤡 Happy April Fools\' Day!', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503152101511.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503152108174.jpg', 1, 433, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-04-15 15:21:08', '2024-05-16 20:33:21', 4, 'B2', 3, '动画', 0, 0);
INSERT INTO `vms_video` VALUES (43, '9岁埃兹拉·弗雷奇的励志故事', 'The Inspirational Story Of 9 Year Old Ezra Frech', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503152210129.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503152222197.jpg', 1, 267, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 15:22:22', '2024-05-03 15:22:22', 2, 'A2', 4, '儿童', 0, 0);
INSERT INTO `vms_video` VALUES (44, '《海底总动员2：多莉去哪儿》的可爱片段', 'Finding Dory Adorable Clips _ Disney', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503152428604.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503152433722.jpg', 1, 320, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 15:24:34', '2024-05-03 15:24:34', 2, 'A2', 4, '儿童', 0, 0);
INSERT INTO `vms_video` VALUES (45, '你实际上需要多少睡眠', 'How Much Sleep Do You Actually Need', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503153432926.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503153433937.jpg', 1, 203, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 15:34:34', '2024-05-03 15:34:34', 4, 'B2', 5, '科学技术', 0, 0);
INSERT INTO `vms_video` VALUES (46, '如何更好地醒来', 'How To Wake Up Better', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503153517742.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503153519216.jpg', 1, 132, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 15:35:19', '2024-05-03 15:35:19', 3, 'B1', 5, '科学技术', 0, 0);
INSERT INTO `vms_video` VALUES (47, 'Tableau与Excel', 'Tableau vs Excel_ When to use Tableau and when to use Excel', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503153648243.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503153649523.jpg', 1, 243, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 15:36:49', '2024-05-03 15:36:49', 3, 'B1', 5, '科学技术', 0, 0);
INSERT INTO `vms_video` VALUES (48, '创业的十大误区', 'The 10 Myths of Entrepreneurship', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503153747245.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503153757956.jpg', 1, 618, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 15:37:58', '2024-05-03 15:37:58', 3, 'B1', 5, '科学技术', 0, 0);
INSERT INTO `vms_video` VALUES (49, '以牙还牙\" - (为什么男人盯着胸部看)', 'Tit for Tat - ( Why Men Stare at Breasts ) {The Kloons}', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240503153925286.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240503153926656.jpg', 1, 156, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-03 15:39:27', '2024-05-03 15:39:27', 4, 'B2', 5, '科学技术', 0, 0);
INSERT INTO `vms_video` VALUES (50, '让生活摆脱杂乱无章的状态 » 你可以问自己的10个问题', 'DECLUTTER YOUR LIFE » 10 questions to ask yourself', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240504151302816.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504151306026.jpg', 1, 286, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-04 15:13:06', '2024-05-04 15:13:06', 3, 'B1', 8, '休闲旅行', 0, 0);
INSERT INTO `vms_video` VALUES (51, '在台湾学习', 'Study in Taiwan --- Learning plus adventure', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240504151413091.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504151419819.jpg', 1, 276, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-04 15:14:20', '2024-05-04 15:14:20', 4, 'B2', 8, '休闲旅行', 0, 0);
INSERT INTO `vms_video` VALUES (52, '台湾必尝的传统美食', 'Traditional Foods to Try in Taiwan (台灣美食)', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240504151600118.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504151622748.jpg', 1, 464, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-04 15:16:22', '2024-05-04 15:16:22', 4, 'B2', 8, '休闲旅行', 0, 0);
INSERT INTO `vms_video` VALUES (53, '如何与陌生人交谈', 'Travel Tips_ How to Talk to Strangers', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240504151821328.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504151823270.jpg', 1, 210, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-04 15:18:23', '2024-05-04 15:18:23', 2, 'A2', 8, '休闲旅行', 0, 0);
INSERT INTO `vms_video` VALUES (54, '为什么日本有这么多自动贩卖机', 'Why Japan has so many vending machines', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240504151934113.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504151942473.jpg', 1, 286, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-04 15:19:42', '2024-05-04 15:19:42', 5, 'C1', 8, '休闲旅行', 0, 0);
INSERT INTO `vms_video` VALUES (55, '我如何在2023年进行自我约束', 'How I Disciplined Myself in 2023', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240504152302096.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504152303259.jpg', 1, 255, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-04 15:23:03', '2024-05-04 15:23:03', 4, 'B2', 9, '健康福祉', 0, 0);
INSERT INTO `vms_video` VALUES (56, '完美主义者的陷阱', 'The Perfectionist Trap', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240504152359888.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504152400627.jpg', 1, 227, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-04 15:24:00', '2024-05-04 15:24:00', 5, 'C1', 9, '健康福祉', 0, 0);
INSERT INTO `vms_video` VALUES (57, '习惯的力量', 'The Power of Habit', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240504152457257.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504152457859.jpg', 1, 175, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-04 15:24:57', '2024-05-04 15:24:57', 4, 'B2', 9, '健康福祉', 0, 0);
INSERT INTO `vms_video` VALUES (58, '日元创下34年新低对日本公司、消费者和旅游业的意义', 'What does the yen at a 34-year low mean for Japanese companies, consumers and tourism', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240504152857421.mp4', 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240504152859929.jpg', 1, 255, 0, 0, 0, 0, 0, 0, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '2024-05-04 15:29:00', '2024-05-04 15:29:00', 6, 'C2', 10, '新闻时事', 0, 0);

-- ----------------------------
-- Table structure for vms_video_comment
-- ----------------------------
DROP TABLE IF EXISTS `vms_video_comment`;
CREATE TABLE `vms_video_comment`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `parent_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父评论的id，0代表无父评论',
  `video_id` int(11) UNSIGNED NOT NULL COMMENT '视频id，外键，关联到视频表',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '用户id，外键，关联到用户表',
  `user_nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像，url',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态（0：待审核，1：已发布，2：已删除）',
  `likes` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '点赞数',
  `replies` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '回复数',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_video_comment
-- ----------------------------
INSERT INTO `vms_video_comment` VALUES (1, 0, 2, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '江西彩礼是真逆天', 1, 2, 2, '2024-04-05 13:48:30', '2024-05-16 09:57:26', NULL);
INSERT INTO `vms_video_comment` VALUES (2, 1, 2, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '不是，哥们你们山东彩礼也没好到哪里去吧', 1, 2, 2, '2024-04-05 13:49:39', '2024-05-16 09:57:28', NULL);
INSERT INTO `vms_video_comment` VALUES (3, 0, 2, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '我觉得还是不应该给那么多彩礼', 2, 1, 1, '2024-04-05 13:50:45', '2024-05-16 09:57:32', NULL);
INSERT INTO `vms_video_comment` VALUES (4, 3, 2, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '支持你，彩礼是旧时代的糟粕', 2, 1, 0, '2024-04-05 13:51:12', '2024-05-16 09:57:35', NULL);
INSERT INTO `vms_video_comment` VALUES (5, 2, 2, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '感觉不如京爷', 1, 0, 0, '2024-04-05 13:54:36', '2024-05-16 09:57:37', NULL);
INSERT INTO `vms_video_comment` VALUES (6, 0, 2, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '我感觉只要有爱情就行了', 1, 0, 0, '2024-04-05 13:54:59', '2024-05-16 09:57:40', NULL);
INSERT INTO `vms_video_comment` VALUES (7, 1, 2, 1, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '泪目', 1, 0, 0, '2024-04-05 13:56:34', '2024-05-16 09:57:41', NULL);
INSERT INTO `vms_video_comment` VALUES (8, 2, 2, 2, '马龙', 'https://lingotube.oss-cn-beijing.aliyuncs.com/user_avatar_20240422160830285.png', '欧美润爷就是强', 1, 0, 0, '2024-04-05 15:26:56', '2024-05-16 09:57:44', NULL);

-- ----------------------------
-- Table structure for vms_video_comment_like
-- ----------------------------
DROP TABLE IF EXISTS `vms_video_comment_like`;
CREATE TABLE `vms_video_comment_like`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '点赞表ID，主键，自增',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '点赞用户的ID，外键，关联到用户表',
  `comment_id` int(11) UNSIGNED NOT NULL COMMENT '被点赞的评论ID，外键，关联到评论表',
  `source` tinyint(1) NULL DEFAULT NULL COMMENT '点赞来源，0：web、1：app',
  `is_liked` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '点赞状态，0：点赞，1：取消点赞',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '点赞时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '点赞状态更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_video_comment_like
-- ----------------------------
INSERT INTO `vms_video_comment_like` VALUES (1, 1, 1, NULL, 1, '2024-04-05 13:53:09', '2024-04-05 13:53:09');
INSERT INTO `vms_video_comment_like` VALUES (2, 1, 2, NULL, 1, '2024-04-05 13:53:12', '2024-04-05 13:53:11');
INSERT INTO `vms_video_comment_like` VALUES (3, 1, 3, NULL, 1, '2024-04-05 13:53:14', '2024-04-05 13:53:13');
INSERT INTO `vms_video_comment_like` VALUES (4, 1, 4, NULL, 1, '2024-04-05 13:53:16', '2024-04-05 13:53:16');
INSERT INTO `vms_video_comment_like` VALUES (5, 2, 1, NULL, 1, '2024-04-05 13:53:34', '2024-04-05 13:53:33');
INSERT INTO `vms_video_comment_like` VALUES (6, 2, 2, NULL, 1, '2024-04-05 13:53:36', '2024-04-05 14:07:25');

-- ----------------------------
-- Table structure for vms_video_level_relation
-- ----------------------------
DROP TABLE IF EXISTS `vms_video_level_relation`;
CREATE TABLE `vms_video_level_relation`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `video_id` int(11) UNSIGNED NOT NULL COMMENT '视频id，外键，关联视频表',
  `level_id` int(11) UNSIGNED NOT NULL COMMENT '等级id，外键，关联等级表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '视频等级关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_video_level_relation
-- ----------------------------

-- ----------------------------
-- Table structure for vms_video_like
-- ----------------------------
DROP TABLE IF EXISTS `vms_video_like`;
CREATE TABLE `vms_video_like`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '用户id，外键，关联用户表',
  `video_id` int(11) UNSIGNED NOT NULL COMMENT '视频id，外键，关联视频表',
  `source` tinyint(1) NULL DEFAULT NULL COMMENT '点赞来源，0：PC端，1：移动端',
  `is_liked` tinyint(1) NOT NULL DEFAULT 1 COMMENT '点赞状态，0：未点赞，1：已点赞',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '点赞时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '点赞状态更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '视频点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_video_like
-- ----------------------------
INSERT INTO `vms_video_like` VALUES (12, 1, 8, NULL, 0, '2024-04-29 11:56:49', '2024-04-29 11:56:49');
INSERT INTO `vms_video_like` VALUES (13, 1, 12, NULL, 0, '2024-04-30 10:17:26', '2024-04-30 10:17:26');
INSERT INTO `vms_video_like` VALUES (14, 1, 3, NULL, 1, '2024-05-04 15:39:29', '2024-05-04 15:39:28');
INSERT INTO `vms_video_like` VALUES (15, 1, 10, NULL, 1, '2024-05-06 22:35:19', '2024-05-06 22:35:19');
INSERT INTO `vms_video_like` VALUES (16, 1, 22, NULL, 1, '2024-05-06 22:36:35', '2024-05-06 22:36:34');

-- ----------------------------
-- Table structure for vms_video_share
-- ----------------------------
DROP TABLE IF EXISTS `vms_video_share`;
CREATE TABLE `vms_video_share`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '分享用户ID，外键，关联到用户表',
  `video_id` int(11) UNSIGNED NOT NULL COMMENT '被分享的视频ID，外键，关联到视频表',
  `platform` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '平台或渠道，如\"WeChat\", \"Twitter\"等',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'URL',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `views` int(11) NULL DEFAULT 0 COMMENT '观看次数',
  `likes` int(11) NULL DEFAULT 0 COMMENT '点赞次数',
  `comments` int(11) NULL DEFAULT 0 COMMENT '评论次数',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除分享：0->不删除；1->删除',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '分享时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '视频分享表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_video_share
-- ----------------------------

-- ----------------------------
-- Table structure for vms_video_subtitle
-- ----------------------------
DROP TABLE IF EXISTS `vms_video_subtitle`;
CREATE TABLE `vms_video_subtitle`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `video_id` int(11) NOT NULL COMMENT '视频id，外键，关联视频表',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `start_time` time(3) NOT NULL COMMENT '开始时间',
  `end_time` time(3) NOT NULL COMMENT '结束时间',
  `color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字体颜色',
  `font_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字体名称',
  `font_size` int(11) NULL DEFAULT NULL COMMENT '字体大小',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_video_subtitle
-- ----------------------------

-- ----------------------------
-- Table structure for vms_video_watch_history
-- ----------------------------
DROP TABLE IF EXISTS `vms_video_watch_history`;
CREATE TABLE `vms_video_watch_history`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '浏览记录ID，主键，自增',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '用户ID，外键，关联用户表',
  `video_id` int(11) UNSIGNED NOT NULL COMMENT '视频ID，外键，关联视频表',
  `view_duration` int(11) NULL DEFAULT 0 COMMENT '已观看的时长（秒）',
  `watch_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '观看时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '视频浏览历史表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_video_watch_history
-- ----------------------------
INSERT INTO `vms_video_watch_history` VALUES (1, 1, 3, 71, '2024-05-04 16:04:04');
INSERT INTO `vms_video_watch_history` VALUES (3, 1, 5, 0, '2024-04-25 13:21:48');
INSERT INTO `vms_video_watch_history` VALUES (4, 1, 6, 0, '2024-04-25 13:21:36');
INSERT INTO `vms_video_watch_history` VALUES (5, 1, 1, 0, '2024-04-25 16:49:40');
INSERT INTO `vms_video_watch_history` VALUES (6, 1, 2, 0, '2024-04-30 11:14:17');
INSERT INTO `vms_video_watch_history` VALUES (7, 1, 4, 0, '2024-04-25 12:03:17');
INSERT INTO `vms_video_watch_history` VALUES (8, 1, 9, 1241, '2024-04-29 15:57:09');
INSERT INTO `vms_video_watch_history` VALUES (9, 1, 8, 4, '2024-04-13 11:02:37');
INSERT INTO `vms_video_watch_history` VALUES (10, 1, 21, 6, '2024-05-14 17:11:50');
INSERT INTO `vms_video_watch_history` VALUES (11, 1, 12, 1764, '2024-04-30 10:59:11');
INSERT INTO `vms_video_watch_history` VALUES (12, 1, 25, 4, '2024-05-03 14:23:09');
INSERT INTO `vms_video_watch_history` VALUES (13, 1, 24, 59, '2024-05-13 09:48:19');
INSERT INTO `vms_video_watch_history` VALUES (14, 1, 23, 34, '2024-05-16 20:32:26');
INSERT INTO `vms_video_watch_history` VALUES (15, 1, 22, 24, '2024-05-06 22:36:57');
INSERT INTO `vms_video_watch_history` VALUES (16, 1, 19, 39, '2024-05-06 22:38:12');
INSERT INTO `vms_video_watch_history` VALUES (17, 1, 42, 162, '2024-05-16 20:36:33');

-- ----------------------------
-- Table structure for vms_video_watch_later
-- ----------------------------
DROP TABLE IF EXISTS `vms_video_watch_later`;
CREATE TABLE `vms_video_watch_later`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识ID',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '用户id，外键，关联用户表',
  `video_id` int(11) UNSIGNED NOT NULL COMMENT '视频id，外键，关联视频表',
  `is_watched` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已观看：0->未观看 ；1->已观看',
  `view_duration` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '已观看的时长（秒）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '视频稍后再看表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vms_video_watch_later
-- ----------------------------
INSERT INTO `vms_video_watch_later` VALUES (3, 1, 19, 1, 9, '2024-05-06 22:37:44');

SET FOREIGN_KEY_CHECKS = 1;
