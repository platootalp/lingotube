package com.moncoder.lingo.common.constant;

/**
 * @author Moncoder
 * @version 1.0
 * @description 视频模块常量
 * @date 2024/3/30 13:09
 */
public interface VideoConstant {

    /**
     * 视频存放前缀
     */
    String VMS_VIDEO_PREFIX = "video_";

    /**
     * 缩略图存放前缀
     */
    String VMS_VIDEO_THUMBNAIL_PREFIX = "video_thumbnail_";

    /**
     * 收藏夹封面存放路径
     */
    String VMS_FAVORITE_FOLDER_PREFIX = "video_favorites_";

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
