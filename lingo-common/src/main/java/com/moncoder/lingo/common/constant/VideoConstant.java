package com.moncoder.lingo.common.constant;

/**
 * @author Moncoder
 * @version 1.0
 * @description 视频模块常量
 * @date 2024/3/30 13:09
 */
public interface VideoConstant {
    /**
     * 收藏夹封面存放路径
     */
    String VMS_FAVORITE_FOLDER_PATH = "uploads/vms/favorites/";

    /**
     * 视频存放路径
     */
    String VMS_VIDEO_PATH = "uploads/video/";

    /**
     * 缩略图路径
     */
    String VMS_THUMBNAIL_PATH = "uploads/thumbnail/";

    /**
     * 视频缩略图存放路径
     */
    String VMS_VIDEO_THUMBNAIL_PATH = "uploads/vms/video/thumbnail/";


    /**
     * 首页热门视频键名
     */
    String VMS_HOME_TRENDING_VIDEO_KEY = "lingo:vms:home:video:trending:";

    /**
     * 首页最新视频键名
     */
    String VMS_HOME_LATEST_VIDEO_KEY = "lingo:vms:home:video:latest:";

    /**
     * 首页推荐视频键名
     */
    String VMS_HOME_RECOMMENDED_VIDEO_KEY = "lingo:vms:home:video:recommended:";

    /**
     * 首页视频键过期时间
     */
    Long VMS_HOME_VIDEO_EXPIRE = 7 * 24 * 60 * 60L;
}
