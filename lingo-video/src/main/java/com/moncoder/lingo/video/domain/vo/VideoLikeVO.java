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
 * @description 赞过的视频返回值
 * @date 2024/4/3 19:33
 */
@Getter
@Setter
@ApiModel("赞过的视频返回值")
public class VideoLikeVO {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("点赞时间")
    private LocalDateTime createTime;
    @ApiModelProperty("视频列表")
    private VmsVideo video;
}
