package com.moncoder.lingo.vocabulary.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO
 * @date 2024/6/5 15:08
 */
@Data
@ApiModel("单词分页查询返回值")
public class WordViewVO {

    @ApiModelProperty("单词id")
    private Integer id;
    @ApiModelProperty("单词名")
    private String name;
    @ApiModelProperty("英式音标")
    private String phoneticUk;
    @ApiModelProperty("美式音标")
    private String phoneticUs;
    @ApiModelProperty
    private Map<String,String> definition;
}
