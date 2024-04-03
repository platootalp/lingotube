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
 * @description TODO 视频浏览历史记录返回值
 * @date 2024/4/3 14:05
 */
@Getter
@Setter
@ApiModel("视频浏览历史记录返回值")
public class VideoBrowseHistoryVO {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("用户观看视频的总时长（秒）")
    private Integer viewDuration;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("视频列表")
    private VmsVideo video;
}
