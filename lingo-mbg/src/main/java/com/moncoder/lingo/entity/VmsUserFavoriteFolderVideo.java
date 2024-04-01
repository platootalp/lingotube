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
 * 用户收藏视频记录表
 * </p>
 *
 * @author moncoder
 * @since 2024-04-01 13:42:53
 */
@Getter
@Setter
@TableName("vms_user_favorite_folder_video")
@ApiModel(value = "VmsUserFavoriteFolderVideo对象", description = "用户收藏视频记录表")
public class VmsUserFavoriteFolderVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收藏记录ID，主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户ID，外键，关联用户表")
    private Integer userId;

    @ApiModelProperty("视频ID，外键，关联视频表")
    private Integer videoId;

    @ApiModelProperty("收藏夹ID，外键，关联收藏夹表")
    private Integer folderId;

    @ApiModelProperty("上传时间")
    private LocalDateTime createTime;
}
