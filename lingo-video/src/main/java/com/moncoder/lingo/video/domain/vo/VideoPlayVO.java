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
 * @description 视频播放返回值
 * @date 2024/4/18 17:11
 */
@Getter
@Setter
@ApiModel("视频播放返回值")
public class VideoPlayVO {
    @ApiModelProperty("视频标题")
    private String title;
    @ApiModelProperty("视频描述")
    private String description;
    @ApiModelProperty("视频URL")
    private String videoUrl;
    @ApiModelProperty("缩略图URL")
    private String thumbnailUrl;
    @ApiModelProperty("视频时长（秒）")
    private Integer duration;
    @ApiModelProperty("观看次数")
    private Integer views;
    @ApiModelProperty("点赞次数")
    private Integer likes;
    @ApiModelProperty("点踩次数")
    private Integer dislikes;
    @ApiModelProperty("收藏次数")
    private Integer favorites;
    @ApiModelProperty("分享次数")
    private Integer shares;
    @ApiModelProperty("评论次数")
    private Integer comments;
    @ApiModelProperty("上传时间")
    private LocalDateTime createTime;
    @ApiModelProperty("视频等级")
    private String levelName;
    @ApiModelProperty("上传者ID，外键，关联用户表")
    private Integer uploaderId;
    @ApiModelProperty("上传者昵称")
    private String uploaderNickname;
    @ApiModelProperty("上传者头像")
    private String uploaderAvatar;
}
