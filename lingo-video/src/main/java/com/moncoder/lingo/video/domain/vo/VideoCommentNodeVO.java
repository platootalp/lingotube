package com.moncoder.lingo.video.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Moncoder
 * @version 1.0
 * @description 视频评论结点
 * @date 2024/4/5 13:13
 */
@Getter
@Setter
@ApiModel("视频评论结点")
public class VideoCommentNodeVO extends VideoCommentVO {
    @ApiModelProperty("子评论列表")
    private List<VideoCommentNodeVO> childrenNodes;
}
