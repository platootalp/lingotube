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
 * 电视剧与视频集数关联表
 * </p>
 *
 * @author moncoder
 * @since 2024-03-28 15:51:18
 */
@Getter
@Setter
@TableName("vms_series_video_relation")
@ApiModel(value = "VmsSeriesVideoRelation对象", description = "电视剧与视频集数关联表")
public class VmsSeriesVideoRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("电视剧表ID")
    private Integer seriesId;

    @ApiModelProperty("视频表ID")
    private Integer videoId;

    @ApiModelProperty("电视剧的第几集")
    private Integer episode;
}
