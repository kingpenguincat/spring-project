package com.longmaominsu.project.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.longmaominsu.project.common.security.SystemRealm;
import com.longmaominsu.project.modules.sys.utils.UserUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by liupeng on 2019/10/6 9:49 PM.
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);
    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ....");
        this.setFieldValByName("create_date", new Date(System.currentTimeMillis()), metaObject);//版本号3.0.6以及之前的版本

        if(UserUtils.getUser()!=null){
            this.setFieldValByName("create_by", UserUtils.getUser().getId().toString(), metaObject);
        }else{
            this.setFieldValByName("create_by", "system", metaObject);
        }




        //this.setInsertFieldValByName("operator", "Jerry", metaObject);//@since 快照：3.0.7.2-SNAPSHOT， @since 正式版暂未发布3.0.7
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("start update fill ....");
        //版本号3.0.6以及之前的版本
        this.setFieldValByName("update_date", new Date(System.currentTimeMillis()), metaObject);
        this.setFieldValByName("update_by", UserUtils.getUser().getId().toString(), metaObject);
        //this.setUpdateFieldValByName("operator", "Tom", metaObject);//@since 快照：3.0.7.2-SNAPSHOT， @since 正式版暂未发布3.0.7
    }
}
