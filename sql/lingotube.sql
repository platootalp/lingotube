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

 Date: 16/03/2024 20:38:50
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
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码（加密存储）',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像，存储url',
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人介绍',
  `gender` tinyint(1) NOT NULL COMMENT '性别：0->未知；1->男；2->女',
  `birthday` date NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_user_favorite_folder
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_favorite_folder`;
CREATE TABLE `ums_user_favorite_folder`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏夹ID，主键，自增',
  `user_id` int(11) NOT NULL COMMENT '用户ID，外键，关联用户表',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收藏夹名称',
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面图片URL或路径',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为默认收藏夹，1为默认，0为非默认',
  `is_public` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为公开收藏夹，1为公开，0为私有',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户收藏夹表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_user_favorite_video
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_favorite_video`;
CREATE TABLE `ums_user_favorite_video`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏记录ID，主键，自增',
  `user_id` int(11) NOT NULL COMMENT '用户ID，外键，关联用户表',
  `video_id` int(11) NOT NULL COMMENT '视频ID，外键，关联视频表',
  `favorite_folder_id` int(11) NOT NULL COMMENT '收藏夹ID，外键，关联收藏夹表',
  `create_time` datetime(0) NOT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户收藏视频记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_user_liked_comment
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_liked_comment`;
CREATE TABLE `ums_user_liked_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞表ID，主键，自增',
  `user_id` int(11) NOT NULL COMMENT '点赞用户的ID，外键，关联到用户表',
  `comment_id` int(11) NOT NULL COMMENT '被点赞的评论ID，外键，关联到评论表',
  `source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '点赞来源，如\"web\"、\"app\"等',
  `is_active` tinyint(4) NOT NULL DEFAULT 1 COMMENT '点赞状态，1->点赞，0->取消点赞',
  `liked_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_user_liked_video
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_liked_video`;
CREATE TABLE `ums_user_liked_video`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID，外键，关联用户表',
  `video_id` int(11) NOT NULL COMMENT '视频ID，外键，关联视频表',
  `liked_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '赞过的视频表' ROW_FORMAT = Dynamic;

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
-- Table structure for ums_user_shared_video
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_shared_video`;
CREATE TABLE `ums_user_shared_video`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分享ID，主键，自增',
  `user_id` int(11) NOT NULL COMMENT '分享用户的ID，外键，关联到用户表',
  `video_id` int(11) NOT NULL COMMENT '被分享的视频ID，外键，关联到视频表',
  `share_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '分享时间',
  `share_platform` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分享的平台或渠道，如\"WeChat\", \"Twitter\"等',
  `share_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分享的链接或URL',
  `share_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分享的内容或描述',
  `views` int(11) NULL DEFAULT 0 COMMENT '分享的观看次数',
  `likes` int(11) NULL DEFAULT 0 COMMENT '分享的点赞次数',
  `comments` int(11) NULL DEFAULT 0 COMMENT '分享的评论次数',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除分享：0->不删除；1->删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分享视频表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_user_video_browsing_history
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_video_browsing_history`;
CREATE TABLE `ums_user_video_browsing_history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '浏览记录ID，主键，自增',
  `user_id` int(11) NOT NULL COMMENT '用户ID，外键，关联用户表',
  `video_id` int(11) NOT NULL COMMENT '视频ID，外键，关联视频表',
  `viewing_duration` int(11) NULL DEFAULT 0 COMMENT '用户观看视频的总时长（秒）',
  `create_time` datetime(0) NOT NULL COMMENT '用户开始浏览视频的时间戳',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户浏览视频历史记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_user_watch_later_video
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_watch_later_video`;
CREATE TABLE `ums_user_watch_later_video`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID，外键，关联用户表',
  `video_id` int(11) NOT NULL COMMENT '视频ID，外键，关联视频表',
  `is_watched` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已观看：1->已观看；0->未观看',
  `watched_position` int(11) NULL DEFAULT NULL COMMENT '已观看的位置，以秒为单位',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '稍后再看视频表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vms_category
-- ----------------------------
DROP TABLE IF EXISTS `vms_category`;
CREATE TABLE `vms_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类唯一标识ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类描述',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父分类ID（用于支持多级分类）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '电影分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vms_movie
-- ----------------------------
DROP TABLE IF EXISTS `vms_movie`;
CREATE TABLE `vms_movie`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '电影唯一标识ID',
  `video_id` int(11) NULL DEFAULT NULL COMMENT 'video表ID',
  `director` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '导演',
  `actors` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主演列表',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `region_id` int(11) NOT NULL COMMENT 'vms_region表ID',
  `category_id` int(11) NOT NULL COMMENT 'vms_category表ID',
  `score` decimal(3, 1) NULL DEFAULT NULL COMMENT '评分',
  `duration` int(11) NULL DEFAULT NULL COMMENT '电影时长（分钟）',
  `views_count` int(11) NULL DEFAULT 0 COMMENT '观看次数',
  `release_year` int(4) NULL DEFAULT NULL COMMENT '上映年份',
  `release_datetime` datetime(0) NULL DEFAULT NULL COMMENT '上映时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_video_id`(`video_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '电影表' ROW_FORMAT = Dynamic;

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
-- Table structure for vms_series
-- ----------------------------
DROP TABLE IF EXISTS `vms_series`;
CREATE TABLE `vms_series`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '电视剧唯一标识ID',
  `director` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '导演',
  `actors` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主演列表',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `region_id` int(11) NOT NULL COMMENT 'vms_region表ID',
  `category_id` int(11) NOT NULL COMMENT 'vms_category表ID',
  `score` decimal(3, 1) NULL DEFAULT NULL COMMENT '评分',
  `total_episodes` int(11) NOT NULL COMMENT '总集数',
  `views_count` int(11) NULL DEFAULT 0 COMMENT '观看次数',
  `release_year` int(4) NULL DEFAULT NULL COMMENT '上映年份',
  `release_time` datetime(0) NULL DEFAULT NULL COMMENT '上映时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '电视剧表' ROW_FORMAT = Dynamic;

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
-- Table structure for vms_video
-- ----------------------------
DROP TABLE IF EXISTS `vms_video`;
CREATE TABLE `vms_video`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频唯一标识ID',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频标题',
  `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频描述',
  `video_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频URL或路径',
  `thumbnail_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩略图URL或路径',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '视频类型（0：普通视频，1：电视剧，2：电影）',
  `is_enable` int(1) NOT NULL DEFAULT 1 COMMENT '视频是否下架（1为上架，0为下架）',
  `duration` int(11) NULL DEFAULT NULL COMMENT '视频时长（秒）',
  `views_count` int(11) NULL DEFAULT 0 COMMENT '观看次数',
  `likes_count` int(11) NULL DEFAULT 0 COMMENT '点赞次数',
  `dislikes_count` int(11) NULL DEFAULT 0 COMMENT '点踩次数',
  `favorites_count` int(11) NULL DEFAULT 0 COMMENT '收藏次数',
  `shares_count` int(11) NULL DEFAULT 0 COMMENT '分享次数',
  `comments_count` int(11) NULL DEFAULT 0 COMMENT '评论次数',
  `uploader_id` int(11) NOT NULL COMMENT '上传者ID，外键，关联用户表',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '上传时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `episode` int(11) NULL DEFAULT NULL COMMENT '电视剧的第几集',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '视频表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vms_video_comment
-- ----------------------------
DROP TABLE IF EXISTS `vms_video_comment`;
CREATE TABLE `vms_video_comment`  (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论的唯一标识符，主键，自增',
  `user_id` int(11) NOT NULL COMMENT '评论者的用户ID，外键，关联到用户表',
  `video_id` int(11) NOT NULL COMMENT '评论的视频ID',
  `parent_comment_id` int(11) NULL DEFAULT 0 COMMENT '父评论的ID，0代表无父评论',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论的具体内容',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '评论的状态，0:待审核、1:已发布、2:已删除',
  `like_count` int(11) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `reply_count` int(11) NOT NULL DEFAULT 0 COMMENT '回复数',
  `creat_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '评论创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '评论更新时间',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '评论删除时间',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vms_video_subtitles
-- ----------------------------
DROP TABLE IF EXISTS `vms_video_subtitles`;
CREATE TABLE `vms_video_subtitles`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字幕唯一标识ID',
  `video_id` int(11) NOT NULL COMMENT '视频id',
  `content` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字幕内容',
  `start_time` time(3) NOT NULL COMMENT '字幕开始时间',
  `end_time` time(3) NULL DEFAULT NULL COMMENT '字幕结束时间',
  `font_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字体名称',
  `font_size` int(11) NULL DEFAULT NULL COMMENT '字体大小',
  `color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字体颜色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
