package com.moncoder.lingo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 分享视频表
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
@Getter
@Setter
@TableName("ums_user_video_share")
@ApiModel(value = "UmsUserVideoShare对象", description = "分享视频表")
public class UmsUserVideoShare implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分享ID，主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("分享用户的ID，外键，关联到用户表")
    private Integer userId;

    @ApiModelProperty("被分享的视频ID，外键，关联到视频表")
    private Integer videoId;

    @ApiModelProperty("分享时间")
    private LocalDateTime shareTime;

    @ApiModelProperty("分享的平台或渠道，如\"WeChat\", \"Twitter\"等")
    private String sharePlatform;

    @ApiModelProperty("分享的链接或URL")
    private String shareUrl;

    @ApiModelProperty("分享的内容或描述")
    private String shareContent;

    @ApiModelProperty("分享的观看次数")
    private Integer views;

    @ApiModelProperty("分享的点赞次数")
    private Integer likes;

    @ApiModelProperty("分享的评论次数")
    private Integer comments;

    @ApiModelProperty("是否删除分享：0->不删除；1->删除")
    private Byte isDeleted;
}
