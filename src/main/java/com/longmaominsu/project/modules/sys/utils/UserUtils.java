package com.longmaominsu.project.modules.sys.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longmaominsu.project.common.security.SystemRealm;
import com.longmaominsu.project.modules.sys.entity.SysUser;
import com.longmaominsu.project.modules.sys.service.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * Created by liupeng on 2019/9/19 8:38 PM.
 */
public class UserUtils {
    @Autowired
    private static RedisTemplate redisTemplate;
    @Autowired
    private static UserMapper userMapper;
    /**
     * 根据ID获取用户
     * @param id
     * @return 取不到返回null
     */
    public static SysUser get(Long id){
        ValueOperations<String,SysUser> operations = redisTemplate.opsForValue();
        SysUser user = new SysUser();
        if(redisTemplate.hasKey(id)){
            user = operations.get(id);
            return user;
        }else{
            user = userMapper.selectById(id);
            operations.set(id.toString(),user,5, TimeUnit.HOURS);
            return user;

        }
    }



    /**
     * 获取当前用户
     * @return 取不到返回 new User()
     */
    public static SysUser getUser(){
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return user;
    }

//    public static SystemRealm.Principal getPrincipal(){
//        try{
//            Subject subject = SecurityUtils.getSubject();
//
//            SystemRealm.Principal principal = (SystemRealm.Principal)subject.getPrincipals().getPrimaryPrincipal();
//            if (principal != null){
//                return principal;
//            }
////			subject.logout();
//        }catch (UnavailableSecurityManagerException e) {
//
//        }catch (InvalidSessionException e){
//
//        }
//        return null;
//    }
}
