package com.moncoder.lingo.video.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO 视频上传参数
 * @date 2024/3/30 14:31
 */
public class VmsVideoDTO {

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

    @ApiModelProperty("视频时长（秒）")
    private Integer duration;

    @ApiModelProperty("上传者ID，外键，关联用户表")
    private Integer uploaderId;

    @ApiModelProperty("电视剧的第几集")
    private Integer episode;
}
