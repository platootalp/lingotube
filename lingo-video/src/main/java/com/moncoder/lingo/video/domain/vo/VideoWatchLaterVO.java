package com.moncoder.lingo.video.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Moncoder
 * @version 1.0
 * @description 视频稍后再看返回值
 * @date 2024/4/3 16:05
 */
@Getter
@Setter
@ApiModel("视频稍后再看返回值")
public class VideoWatchLaterVO extends VideoViewVO{
    @ApiModelProperty("是否已观看：0->未观看 ；1->已观看")
    private Byte isWatched;
    @ApiModelProperty("已观看的时长（秒）")
    private Integer viewDuration;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
