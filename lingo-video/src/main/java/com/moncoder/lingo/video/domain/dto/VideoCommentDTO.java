package com.moncoder.lingo.video.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Moncoder
 * @version 1.0
 * @description 视频评论参数
 * @date 2024/4/5 10:15
 */
@Getter
@Setter
@ApiModel("视频评论参数")
public class VideoCommentDTO {

    @NotNull
    @ApiModelProperty("评论的用户ID，外键，关联到用户表")
    private Integer userId;
    @NotNull
    @ApiModelProperty("评论的视频ID，外键，关联到视频表")
    private Integer videoId;
    @NotNull
    @ApiModelProperty("评论的视频ID，外键，关联到视频表")
    private Integer parentId;
    @NotBlank
    @ApiModelProperty("评论的具体内容")
    private String content;
    @NotNull
    @ApiModelProperty("评论的状态，0：待审核、1：已发布、2：已删除")
    private Byte status;
    @NotNull
    @ApiModelProperty("评论创建时间")
    private LocalDateTime createTime;
    @NotNull
    @ApiModelProperty("评论更新时间")
    private LocalDateTime updateTime;
}
