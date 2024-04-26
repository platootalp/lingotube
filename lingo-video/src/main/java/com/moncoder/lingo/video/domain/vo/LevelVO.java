package com.moncoder.lingo.video.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Moncoder
 * @version 1.0
 * @description 视频等级返回值
 * @date 2024/4/26 14:53
 */
@Getter
@Setter
@ApiModel("视频等级返回值")
public class LevelVO {
    @ApiModelProperty("等级唯一标识ID	")
    private Integer id;
    @ApiModelProperty("等级名称")
    private String name;
    @ApiModelProperty("等级描述")
    private String description;
}
