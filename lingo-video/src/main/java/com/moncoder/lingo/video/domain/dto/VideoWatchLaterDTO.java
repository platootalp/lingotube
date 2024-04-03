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
 * @description 视频稍后再看创建参数
 * @date 2024/4/3 15:52
 */
@Getter
@Setter
@ApiModel("视频稍后再看创建参数")
public class VideoWatchLaterDTO {
    @NotNull
    @ApiModelProperty("用户id，外键，关联用户表")
    private Integer userId;
    @NotNull
    @ApiModelProperty("视频id，外键，关联视频表")
    private Integer videoId;
    @NotNull
    @ApiModelProperty("已观看的时长（秒）")
    private Integer viewDuration;
}
