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
 * 单词例句表
 * </p>
 *
 * @author moncoder
 * @since 2024-06-03 13:51:38
 */
@Getter
@Setter
@TableName("wms_word_example")
@ApiModel(value = "WordExample对象", description = "单词例句表")
public class WordExample implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("单词定义id，外键，关联单词定义表")
    private Integer definitionId;

    @ApiModelProperty("英文例句")
    private String sentence;

    @ApiModelProperty("中文翻译")
    private String translation;

    @ApiModelProperty("读音URL")
    private String audioUrl;

    @ApiModelProperty("添加时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后更新时间")
    private LocalDateTime updateTime;
}
