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
 * 电影分类表
 * </p>
 *
 * @author moncoder
 * @since 2024-03-28 14:54:53
 */
@Getter
@Setter
@TableName("vms_category")
@ApiModel(value = "VmsCategory对象", description = "电影分类表")
public class VmsCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类唯一标识ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("分类描述")
    private String description;

    @ApiModelProperty("父分类ID（用于支持多级分类）")
    private Integer parentId;
}
