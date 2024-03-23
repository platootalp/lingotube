package com.moncoder.lingo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
 * @since 2024-03-20 16:27:25
 */
@Getter
@Setter
@TableName("ums_user")
@ApiModel(value = "UmsUser对象", description = "")
public class UmsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码（加密存储）")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像，存储url")
    private String avatar;

    @ApiModelProperty("个人介绍")
    private String introduce;

    @ApiModelProperty("性别：0->未知；1->男；2->女")
    private Byte gender;

    @ApiModelProperty("生日")
    private LocalDate birthday;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("帐号启用状态：0->禁用；1->启用")
    private Byte status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;
}
