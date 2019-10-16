package com.longmaominsu.project.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.longmaominsu.project.common.persistence.BaseEntity;

import lombok.Data;

import java.util.Date;

/**
 * Created by liupeng on 2019/9/20 9:16 PM.
 */
@Data

public class SysUser extends BaseEntity<SysUser> {
    private String name;
    private String password;
    private String nickName;
    private String realName;
    private String address;
    private int gender;
    private String role;
    private Date registerTime;
    private Date loginTime;
    private String accessToken;
    private String WechatOpenId;
    private String remarks;
    private int status;
}
