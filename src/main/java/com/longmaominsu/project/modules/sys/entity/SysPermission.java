package com.longmaominsu.project.modules.sys.entity;

import com.longmaominsu.project.common.persistence.BaseEntity;
import lombok.Data;

/**
 * Created by liupeng on 2019/9/20 9:33 PM.
 */
@Data
public class SysPermission extends BaseEntity<SysPermission>{
    private String value;
    private int status;

}
