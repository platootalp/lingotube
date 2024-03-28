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
 * 用户浏览视频历史记录表
 * </p>
 *
 * @author moncoder
 * @since 2024-03-28 14:54:53
 */
@Getter
@Setter
@TableName("vms_video_browse_history")
@ApiModel(value = "VmsVideoBrowseHistory对象", description = "用户浏览视频历史记录表")
public class VmsVideoBrowseHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("浏览记录ID，主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户ID，外键，关联用户表")
    private Integer userId;

    @ApiModelProperty("视频ID，外键，关联视频表")
    private Integer videoId;

    @ApiModelProperty("用户观看视频的总时长（秒）")
    private Integer viewingDuration;

    @ApiModelProperty("用户开始浏览视频的时间")
    private LocalDateTime createTime;
}
