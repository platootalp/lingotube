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
 * 评论表
 * </p>
 *
 * @author moncoder
 * @since 2024-04-05 10:32:24
 */
@Getter
@Setter
@TableName("vms_video_comment")
@ApiModel(value = "VmsVideoComment对象", description = "评论表")
public class VmsVideoComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论的唯一标识符，主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("评论的用户ID，外键，关联到用户表")
    private Integer userId;

    @ApiModelProperty("评论的视频ID，外键，关联到视频表")
    private Integer videoId;

    @ApiModelProperty("父评论的ID，0代表无父评论")
    private Integer parentId;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("评论的具体内容")
    private String content;

    @ApiModelProperty("评论的状态，0：待审核、1：已发布、2：已删除")
    private Byte status;

    @ApiModelProperty("点赞数")
    private Integer likes;

    @ApiModelProperty("回复数")
    private Integer replies;

    @ApiModelProperty("评论创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("评论更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("评论删除时间")
    private LocalDateTime deleteTime;
}
