package com.longmaominsu.project.modules.sys.entity;

import com.longmaominsu.project.common.persistence.BaseEntity;
import lombok.Data;

/**
 * Created by liupeng on 2019/9/20 9:27 PM.
 */
@Data
public class SysRole extends BaseEntity<SysRole> {
    private String name;
    private String permission;
    private String status;

}
