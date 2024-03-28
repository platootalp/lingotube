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
 * 地区表
 * </p>
 *
 * @author moncoder
 * @since 2024-03-28 14:54:53
 */
@Getter
@Setter
@TableName("vms_region")
@ApiModel(value = "VmsRegion对象", description = "地区表")
public class VmsRegion implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("地区ID，主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("地区名称")
    private String name;

    @ApiModelProperty("地区描述（可选）")
    private String description;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;
}
