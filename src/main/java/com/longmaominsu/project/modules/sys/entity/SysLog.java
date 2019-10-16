package com.longmaominsu.project.modules.sys.entity;

import com.longmaominsu.project.common.persistence.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * Created by liupeng on 2019/10/7 11:24 AM.
 */
@Data
public class SysLog extends BaseEntity<SysLog>{

    private int type;
    private String title;
    private String remoteIp;
    private String info;
}
