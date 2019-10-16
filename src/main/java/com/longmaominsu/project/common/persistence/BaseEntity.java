package com.longmaominsu.project.common.persistence;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liupeng on 2019/9/19 8:17 PM.
 */
public abstract class BaseEntity<T> implements Serializable{
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;


    @TableField(fill = FieldFill.INSERT)
    private Date create_date;
    @TableField(fill= FieldFill.UPDATE)
    private Date update_date;
    @TableField(fill= FieldFill.INSERT)
    private String create_by;
    @TableField(fill= FieldFill.UPDATE)
    private String update_by;


}
