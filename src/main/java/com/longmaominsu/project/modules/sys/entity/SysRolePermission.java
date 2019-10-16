package com.longmaominsu.project.modules.sys.entity;

import com.longmaominsu.project.common.persistence.BaseEntity;

import lombok.Data;

/**
 * Created by liupeng on 2019/9/20 9:34 PM.
 */
@Data
public class SysRolePermission extends BaseEntity<SysRolePermission> {
    private String roleId;
    private String permissionId;
}
