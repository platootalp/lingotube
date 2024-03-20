package com.moncoder.lingo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
 * @since 2024-03-20 14:59:05
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

    private LocalDate birthday;

    private String email;

    private String phone;

    private String address;

    @ApiModelProperty("帐号启用状态：0->禁用；1->启用")
    private Byte status;
}
