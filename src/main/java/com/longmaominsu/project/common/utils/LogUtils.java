package com.longmaominsu.project.common.utils;

import com.longmaominsu.project.modules.sys.entity.SysLog;
import com.longmaominsu.project.modules.sys.entity.SysUser;
import com.longmaominsu.project.modules.sys.service.LogMapper;
import com.longmaominsu.project.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by liupeng on 2019/10/7 11:19 AM.
 */
public class LogUtils {


    private static LogMapper logMapper = SpringContextHolder.getBean(LogMapper.class);

    public static void saveLog(String title){
        saveLog(1,title,null);
    }

    public static void saveLog(int type,String title, String info){

            SysLog log = new SysLog();

            log.setType(type);
            log.setTitle(title);
            log.setInfo(info);
            log.setRemoteIp(SecurityUtils.getSubject().getSession().getHost());
            logMapper.insert(log);
            //new SaveLogThread(log).start();
            //new Thread(()->saveLogFun(log)).start();

    }


    public static void saveLogFun(SysLog log){
        //logMapper.insert(log);
    }

    /**
     * 保存日志线程
     */
    public static class SaveLogThread extends Thread{
        private SysLog log;

        public SaveLogThread(SysLog log){
            super(SaveLogThread.class.getSimpleName());
            this.log = log;

        }

        @Override
        public void run() {
            // 获取日志标题
            if (StringUtils.isBlank(log.getTitle())){


            }

        }
    }









}
