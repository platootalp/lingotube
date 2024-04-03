package com.moncoder.lingo.video.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO 用户收藏夹视频复制参数
 * @date 2024/4/1 13:56
 */
@Getter
@Setter
@ApiModel("用户收藏夹视频复制参数")
public class FavoriteFolderVideoCopyDTO {

    @NotNull
    @ApiModelProperty("用户id")
    private Integer userId;

    @NotNull
    @ApiModelProperty("收藏视频id集合")
    private List<Integer> videoIds;

    @NotNull
    @ApiModelProperty("当前收藏夹id")
    private Integer curFolderId;

    @NotNull
    @ApiModelProperty("新收藏夹id集合")
    private List<Integer> newFolderIds;
}
