package com.moncoder.lingo.video.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Moncoder
 * @version 1.0
 * @description 视频评论返回值
 * @date 2024/4/5 14:10
 */
@Getter
@Setter
@ApiModel("视频评论返回值")
public class VideoCommentVO {

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("父评论的ID，0代表无父评论")
    private Integer parentId;

    @ApiModelProperty("评论的具体内容")
    private String content;

    @ApiModelProperty("点赞数")
    private Integer likes;

    @ApiModelProperty("回复数")
    private Integer replies;

}
