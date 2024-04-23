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
 * 视频浏览历史表
 * </p>
 *
 * @author moncoder
 * @since 2024-04-08 15:37:19
 */
@Getter
@Setter
@TableName("vms_video_watch_history")
@ApiModel(value = "VmsVideoWatchHistory对象", description = "视频浏览历史表")
public class VmsVideoWatchHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("浏览记录ID，主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户ID，外键，关联用户表")
    private Integer userId;

    @ApiModelProperty("视频ID，外键，关联视频表")
    private Integer videoId;

    @ApiModelProperty("已观看的时长（秒）")
    private Integer viewDuration;

    @ApiModelProperty("观看时间")
    private LocalDateTime watchTime;
}
