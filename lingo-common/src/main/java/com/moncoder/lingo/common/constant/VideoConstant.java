package com.moncoder.lingo.common.constant;

/**
 * @author Moncoder
 * @version 1.0
 * @description 视频模块常量
 * @date 2024/3/30 13:09
 */
public interface VideoConstant {

    /**
     * 视频文件url前缀
     */
    String VMS_VIDEO_PREFIX = "video_";

    /**
     * 缩略图文件url前缀
     */
    String VMS_VIDEO_THUMBNAIL_PREFIX = "video_thumbnail_";

    /**
     * 收藏夹封面文件url前缀
     */
    String VMS_VIDEO_FAVORITE_FOLDER_PREFIX = "video_fav_folder_";

    /**
     * 每条视频下方最大相关视频数量
     */
    Integer VMS_RELATED_VIDEO_COUNT = 3;

    /**
     * 播放视频键名
     */
    String VMS_VIDEO_PLAYER_KEY = "lingo:vms:video:player:";

    /**
     * 播放视频过期时间
     */
    Long VMS_VIDEO_PLAYER_EXPIRE = 3 * 24 * 60 * 60L;

    /**
     * 首页热门视频键名
     */
    String VMS_VIDEO_HOME_TRENDING_KEY = "lingo:vms:video:home:trending:";

    /**
     * 首页最新视频键名
     */
    String VMS_VIDEO_HOME_LATEST_KEY = "lingo:vms:video:home:latest:";

    /**
     * 首页推荐视频键名
     */
    String VMS_VIDEO_HOME_RECOMMENDED_KEY = "lingo:vms:video:home:recommended:";

    /**
     * 首页视频键过期时间
     */
    Long VMS_VIDEO_HOME_EXPIRE = 7 * 24 * 60 * 60L;


    /**
     * 视频分类键名
     */
    String VMS_VIDEO_CATEGORY_KEY = "lingo:vms:video:category";

    /**
     * 视频等级键名
     */
    String VMS_VIDEO_LEVEL_KEY = "lingo:vms:video:level";
}
