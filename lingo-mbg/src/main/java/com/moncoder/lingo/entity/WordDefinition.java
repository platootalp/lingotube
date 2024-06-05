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
 * 单词释义表
 * </p>
 *
 * @author moncoder
 * @since 2024-06-03 13:51:38
 */
@Getter
@Setter
@TableName("wms_word_definition")
@ApiModel(value = "WordDefinition对象", description = "单词释义表")
public class WordDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("单词id，外键，关联单词表")
    private Integer wordId;

    @ApiModelProperty("词性英文名")
    private String posEn;

    @ApiModelProperty("词性中文名")
    private String posCn;

    @ApiModelProperty("单词释义")
    private String meaning;

    @ApiModelProperty("添加时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后更新时间")
    private LocalDateTime updateTime;
}
