package com.moncoder.lingo.video.domain.vo;

import com.moncoder.lingo.entity.VmsVideo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Moncoder
 * @version 1.0
 * @description  用户收藏的视频返回值参数
 * @date 2024/3/28 15:29
 */
@Getter
@Setter
@ApiModel("用户收藏的视频返回值参数")
public class FavoriteVideoVO {

    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("收藏时间")
    private LocalDateTime favoriteTime;
    @ApiModelProperty("视频列表")
    private VmsVideo video;

}
