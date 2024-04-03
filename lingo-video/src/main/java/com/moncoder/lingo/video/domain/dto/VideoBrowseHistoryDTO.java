package com.moncoder.lingo.video.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Moncoder
 * @version 1.0
 * @description 视频浏览历史记录创建参数
 * @date 2024/4/3 13:48
 */
@Getter
@Setter
@ApiModel("视频浏览历史记录创建参数")
public class VideoBrowseHistoryDTO {

    @NotNull
    @ApiModelProperty("用户ID，外键，关联用户表")
    private Integer userId;

    @NotNull
    @ApiModelProperty("视频ID，外键，关联视频表")
    private Integer videoId;

    @NotNull
    @ApiModelProperty("用户观看视频的总时长（秒）")
    private Integer viewDuration;
}
