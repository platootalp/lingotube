package com.moncoder.lingo.video.domain.vo;

import com.moncoder.lingo.entity.VmsVideo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Moncoder
 * @version 1.0
 * @description 视频观看历史返回值
 * @date 2024/4/3 14:05
 */
@Getter
@Setter
@ApiModel("视频观看历史返回值")
public class VideoWatchHistoryVO extends VideoViewVO {
    @ApiModelProperty("已观看的时长（秒）")
    private Integer viewDuration;
    @ApiModelProperty("创建时间")
    private LocalDateTime watchTime;
}
