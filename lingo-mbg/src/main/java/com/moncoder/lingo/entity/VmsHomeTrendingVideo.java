package com.moncoder.lingo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 最热视频表
 * </p>
 *
 * @author moncoder
 * @since 2024-04-14 14:50:39
 */
@Getter
@Setter
@TableName("vms_home_trending_video")
@ApiModel(value = "VmsHomeTrendingVideo对象", description = "最热视频表")
public class VmsHomeTrendingVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("视频ID，外键，参考主视频表中的视频唯一标识")
    private Integer videoId;

    @ApiModelProperty("视频标题")
    private String title;

    @ApiModelProperty("缩略图URL")
    private String thumbnailUrl;

    @ApiModelProperty("视频时长（秒）")
    private Integer duration;

    @ApiModelProperty("观看次数")
    private Integer views;

    @ApiModelProperty("视频等级")
    private String levelName;

    @ApiModelProperty("是否上架首页（0：下架，1：上架）")
    private Byte status;
}
