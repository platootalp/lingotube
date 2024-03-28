package com.moncoder.lingo.user.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.moncoder.lingo.common.annotation.ZeroOrOne;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * @author Moncoder
 * @version 1.0
 * @description 用户收藏夹创建参数
 * @date 2024/3/28 10:17
 */
@Getter
@Setter
@ApiModel("用户收藏夹创建参数")
public class UserFavoriteFolderDTO {

    @NotNull
    @ApiModelProperty(value = "用户ID，外键，关联用户表",required = true)
    private Integer userId;

    @NotBlank
    @ApiModelProperty(value = "收藏夹名称",required = true)
    private String name;

    @ApiModelProperty("封面图片uri")
    private String coverImage;

    @ApiModelProperty("简介")
    private String description;

    @ZeroOrOne
    @ApiModelProperty("是否为默认收藏夹，1为默认，0为非默认")
    private Byte isDefault;

    @ZeroOrOne
    @ApiModelProperty("是否为公开收藏夹，1为公开，0为私有")
    private Byte isPublic;

}
