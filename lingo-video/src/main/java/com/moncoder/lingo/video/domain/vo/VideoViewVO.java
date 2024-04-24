package com.moncoder.lingo.video.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Moncoder
 * @version 1.0
 * @description 视频视图，用于显示视频
 * @date 2024/4/14 13:22
 */
@Getter
@Setter
@ApiModel("视频展示")
public class VideoViewVO {
    @ApiModelProperty("视频id")
    private Integer id;
    @ApiModelProperty("视频标题")
    private String title;
    @ApiModelProperty("缩略图URL或路径")
    private String thumbnailUrl;
    @ApiModelProperty("视频时长（秒）")
    private Integer duration;
    @ApiModelProperty("观看次数")
    private Integer views;
    @ApiModelProperty("视频等级")
    private String levelName;
    @ApiModelProperty("上传者ID，外键，关联用户表")
    private Integer uploaderId;
    @ApiModelProperty("上传者昵称")
    private String uploaderNickname;
    @ApiModelProperty("上传者头像")
    private String uploaderAvatar;
}
