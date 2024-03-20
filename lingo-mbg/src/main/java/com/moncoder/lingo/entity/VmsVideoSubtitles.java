package com.moncoder.lingo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 14:59:05
 */
@Getter
@Setter
@TableName("vms_video_subtitles")
@ApiModel(value = "VmsVideoSubtitles对象", description = "")
public class VmsVideoSubtitles implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字幕唯一标识ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("视频id")
    private Integer videoId;

    @ApiModelProperty("字幕内容")
    private String content;

    @ApiModelProperty("字幕开始时间")
    private LocalTime startTime;

    @ApiModelProperty("字幕结束时间")
    private LocalTime endTime;

    @ApiModelProperty("字体名称")
    private String fontName;

    @ApiModelProperty("字体大小")
    private Integer fontSize;

    @ApiModelProperty("字体颜色")
    private String color;
}
