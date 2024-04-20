package com.moncoder.lingo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 评论点赞表
 * </p>
 *
 * @author moncoder
 * @since 2024-04-05 11:20:05
 */
@Getter
@Setter
@TableName("vms_video_comment_like")
@ApiModel(value = "VmsVideoCommentLike对象", description = "评论点赞表")
public class VmsVideoCommentLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("点赞表ID，主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("点赞用户的ID，外键，关联到用户表")
    private Integer userId;

    @ApiModelProperty("被点赞的评论ID，外键，关联到评论表")
    private Integer commentId;

    @ApiModelProperty("点赞来源，0：web、1：app	")
    private Byte source;

    @ApiModelProperty("点赞状态，0：点赞，1：取消点赞")
    private Byte isLiked;

    @ApiModelProperty("点赞时间")
    private LocalDateTime createTime;

    @ApiModelProperty("点赞状态更新时间")
    private LocalDateTime updateTime;
}
