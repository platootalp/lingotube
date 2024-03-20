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
 * 用户点赞表
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
@Getter
@Setter
@TableName("ums_user_comment_like")
@ApiModel(value = "UmsUserCommentLike对象", description = "用户点赞表")
public class UmsUserCommentLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("点赞表ID，主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("点赞用户的ID，外键，关联到用户表")
    private Integer userId;

    @ApiModelProperty("被点赞的评论ID，外键，关联到评论表")
    private Integer commentId;

    @ApiModelProperty("点赞来源，如\"web\"、\"app\"等")
    private String source;

    @ApiModelProperty("点赞状态，1->点赞，0->取消点赞")
    private Byte isActive;

    @ApiModelProperty("点赞时间")
    private LocalDateTime likedTime;
}
