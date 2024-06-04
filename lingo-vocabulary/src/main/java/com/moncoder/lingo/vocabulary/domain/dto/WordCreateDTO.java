package com.moncoder.lingo.vocabulary.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Moncoder
 * @version 1.0
 * @description 单词创建参数
 * @date 2024/6/3 14:14
 */
@Data
@ApiModel("单词创建参数")
public class WordCreateDTO {
    @NotBlank
    @ApiModelProperty("单词名")
    private String name;

    @NotBlank
    @ApiModelProperty("英式音标")
    private String phoneticUk;

    @NotBlank
    @ApiModelProperty("美式音标")
    private String phoneticUs;

    @NotBlank
    @ApiModelProperty("英式音频文件URL")
    private String pronunciationUrlUk;

    @NotBlank
    @ApiModelProperty("美式音频文件URL")
    private String pronunciationUrlUs;

    @ApiModelProperty("单词词性及对应的意思")
    private Map<String, String> meanings;
}

