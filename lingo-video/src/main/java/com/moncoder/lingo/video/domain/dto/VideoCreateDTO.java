package com.moncoder.lingo.video.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author Moncoder
 * @version 1.0
 * @description 视频上传参数
 * @date 2024/3/30 14:31
 */
@Getter
@Setter
@ApiModel("视频上传参数")
public class VideoCreateDTO {
    @NotBlank
    @ApiModelProperty("视频标题")
    private String title;
    @ApiModelProperty("视频描述")
    private String description;
    @NotBlank
    @ApiModelProperty("视频URL或路径")
    private String videoUrl;
    @NotBlank
    @ApiModelProperty("缩略图URL或路径")
    private String thumbnailUrl;
    @NotNull
    @ApiModelProperty("视频类型（0：普通视频，1：电视剧，2：电影）")
    private Byte type;
    @NotNull
    @ApiModelProperty("视频时长（秒）")
    private Integer duration;
    @NotNull
    @ApiModelProperty("上传者ID，外键，关联用户表")
    private Integer uploaderId;
    @ApiModelProperty("电视剧的第几集")
    private Integer episode;
    @ApiModelProperty("视频等级")
    private String levelName;
}
