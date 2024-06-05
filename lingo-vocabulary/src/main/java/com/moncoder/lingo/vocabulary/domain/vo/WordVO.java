package com.moncoder.lingo.vocabulary.domain.vo;

import com.moncoder.lingo.entity.WordDefinition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Moncoder
 * @version 1.0
 * @description 单词分页查询返回值
 * @date 2024/6/4 17:08
 */
@Data
@ApiModel("单词分页查询返回值")
public class WordVO {

    @ApiModelProperty("单词id")
    private Integer id;
    @ApiModelProperty("单词名")
    private String name;
    @ApiModelProperty("英式音标")
    private String phoneticUk;
    @ApiModelProperty("美式音标")
    private String phoneticUs;
    @ApiModelProperty("词性英文名")
    private String posEn;
    @ApiModelProperty("词性中文名")
    private String posCn;
    @ApiModelProperty("单词释义")
    private String meaning;
}
