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
 * 视频等级关联表
 * </p>
 *
 * @author moncoder
 * @since 2024-04-14 15:08:48
 */
@Getter
@Setter
@TableName("vms_video_level_relation")
@ApiModel(value = "VmsVideoLevelRelation对象", description = "视频等级关联表")
public class VmsVideoLevelRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("视频id，外键，关联视频表")
    private Integer videoId;

    @ApiModelProperty("等级id，外键，关联等级表")
    private Integer levelId;
}
