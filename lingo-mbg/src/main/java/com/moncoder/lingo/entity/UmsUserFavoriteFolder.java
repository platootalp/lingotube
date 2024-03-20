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
 * 用户收藏夹表
 * </p>
 *
 * @author moncoder
 * @since 2024-03-20 16:27:25
 */
@Getter
@Setter
@TableName("ums_user_favorite_folder")
@ApiModel(value = "UmsUserFavoriteFolder对象", description = "用户收藏夹表")
public class UmsUserFavoriteFolder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收藏夹ID，主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户ID，外键，关联用户表")
    private Integer userId;

    @ApiModelProperty("收藏夹名称")
    private String name;

    @ApiModelProperty("封面图片URL或路径")
    private String coverImage;

    @ApiModelProperty("是否为默认收藏夹，1为默认，0为非默认")
    private Byte isDefault;

    @ApiModelProperty("是否为公开收藏夹，1为公开，0为私有")
    private Byte isPublic;

    @ApiModelProperty("简介")
    private String description;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
