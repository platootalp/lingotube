package com.moncoder.lingo.search.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;


/**
 * @author Moncoder
 * @version 1.0
 * @description 搜索视频
 * @date 2024/5/13 14:30
 */
@Data
@Document(indexName = "lingo_video")
@ApiModel("es中存储出的视频")
public class EsVideo {
    @ApiModelProperty("视频唯一标识")
    @Id
    private Integer id;

    @ApiModelProperty("视频标题")
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String title;

    @ApiModelProperty("视频描述")
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String description;

    @ApiModelProperty("缩略图URL")
    @Field(type = FieldType.Keyword)
    private String thumbnailUrl;

    @ApiModelProperty("视频时长（秒）")
    private Integer duration;

    @ApiModelProperty("观看次数")
    private Integer views;

    @ApiModelProperty("上传者ID，外键，关联用户表")
    private Integer uploaderId;

    @ApiModelProperty("上传者昵称")
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String uploaderNickname;

    @ApiModelProperty("上传者头像，存储url")
    @Field(type = FieldType.Keyword)
    private String uploaderAvatar;

    @ApiModelProperty("上传时间")
    @Field(type = FieldType.Date)
    private LocalDateTime uploadTime;

    @ApiModelProperty("等级名称")
    @Field(type = FieldType.Keyword)
    private String levelName;

    @ApiModelProperty("分类名称")
    @Field(type = FieldType.Keyword)
    private String categoryName;
}
