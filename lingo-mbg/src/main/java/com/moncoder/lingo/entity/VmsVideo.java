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
 * 视频表
 * </p>
 *
 * @author moncoder
 * @since 2024-03-28 15:51:18
 */
@Getter
@Setter
@TableName("vms_video")
@ApiModel(value = "VmsVideo对象", description = "视频表")
public class VmsVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("视频唯一标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("视频标题")
    private String title;

    @ApiModelProperty("视频描述")
    private String description;

    @ApiModelProperty("视频URL或路径")
    private String videoUrl;

    @ApiModelProperty("缩略图URL或路径")
    private String thumbnailUrl;

    @ApiModelProperty("视频类型（0：普通视频，1：电视剧，2：电影）")
    private Byte type;

    @ApiModelProperty("视频是否下架（0：下架，1：上架）")
    private Byte enable;

    @ApiModelProperty("视频时长（秒）")
    private Integer duration;

    @ApiModelProperty("观看次数")
    private Integer views;

    @ApiModelProperty("点赞次数")
    private Integer likes;

    @ApiModelProperty("点踩次数")
    private Integer dislikes;

    @ApiModelProperty("收藏次数")
    private Integer favorites;

    @ApiModelProperty("分享次数")
    private Integer shares;

    @ApiModelProperty("评论次数")
    private Integer comments;

    @ApiModelProperty("上传者ID，外键，关联用户表")
    private Integer uploaderId;

    @ApiModelProperty("上传时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("电视剧的第几集")
    private Integer episode;

    @ApiModelProperty("视频等级")
    private String levelName;
}
