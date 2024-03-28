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
 * 
 * </p>
 *
 * @author moncoder
 * @since 2024-03-28 14:54:53
 */
@Getter
@Setter
@TableName("ums_user_login_log")
@ApiModel(value = "UmsUserLoginLog对象", description = "")
public class UmsUserLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("登录日志ID，主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户ID，外键，关联用户表")
    private Integer userId;

    @ApiModelProperty("用户登录时的IP地址")
    private String ip;

    @ApiModelProperty("用户登录时使用的操作系统")
    private String os;

    @ApiModelProperty("用户登录时使用的浏览器")
    private String browser;

    @ApiModelProperty("关于此次登录的额外信息或备注")
    private String description;

    @ApiModelProperty("用户登录时间")
    private LocalDateTime createTime;
}
