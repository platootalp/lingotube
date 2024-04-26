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
 * 视频分类表
 * </p>
 *
 * @author moncoder
 * @since 2024-04-26 14:07:41
 */
@Getter
@Setter
@TableName("vms_category")
@ApiModel(value = "VmsCategory对象", description = "视频分类表")
public class VmsCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类唯一标识ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("分类描述")
    private String description;

    @ApiModelProperty("是否有效，0：无效，1：有效")
    private Byte isEnable;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
