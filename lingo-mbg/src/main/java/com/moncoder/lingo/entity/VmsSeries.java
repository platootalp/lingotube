package com.moncoder.lingo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 电视剧表
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@Getter
@Setter
@TableName("vms_series")
@ApiModel(value = "VmsSeries对象", description = "电视剧表")
public class VmsSeries implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("电视剧唯一标识ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("导演")
    private String director;

    @ApiModelProperty("主演列表")
    private String actors;

    @ApiModelProperty("简介")
    private String description;

    @ApiModelProperty("vms_region表ID")
    private Integer regionId;

    @ApiModelProperty("vms_category表ID")
    private Integer categoryId;

    @ApiModelProperty("评分")
    private BigDecimal score;

    @ApiModelProperty("总集数")
    private Integer totalEpisodes;

    @ApiModelProperty("观看次数")
    private Integer viewsCount;

    @ApiModelProperty("上映年份")
    private Integer releaseYear;

    @ApiModelProperty("上映时间")
    private LocalDateTime releaseTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
