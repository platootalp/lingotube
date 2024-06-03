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
 * 单词表
 * </p>
 *
 * @author moncoder
 * @since 2024-06-03 13:51:38
 */
@Getter
@Setter
@TableName("wms_word")
@ApiModel(value = "Word对象", description = "单词表")
public class Word implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("单词名")
    private String name;

    @ApiModelProperty("英式音标")
    private String phoneticUk;

    @ApiModelProperty("美式音标")
    private String phoneticUs;

    @ApiModelProperty("英式音频文件URL")
    private String pronunciationUrlUk;

    @ApiModelProperty("美式音频文件URL")
    private String pronunciationUrlUs;

    @ApiModelProperty("添加时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("最后更新时间")
    private LocalDateTime updatedTime;
}
