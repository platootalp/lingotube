package com.moncoder.lingo.video.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Moncoder
 * @version 1.0
 * @description 上传视频返回值
 * @date 2024/4/14 16:01
 */
@Getter
@Setter
@ApiModel("上传视频返回值")
public class UploadVideoVo {
    @ApiModelProperty("视频URL或路径")
    private String videoUrl;
    @ApiModelProperty("缩略图URL或路径")
    private String thumbnailUrl;
}
