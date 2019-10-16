package com.longmaominsu.project.common.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longmaominsu.project.common.utils.ConstantInfo;
import com.longmaominsu.project.common.utils.LogUtils;
import com.longmaominsu.project.modules.sys.entity.SysUser;
import com.longmaominsu.project.modules.sys.service.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by liupeng on 2019/9/20 4:56 PM.
 */
public class SystemRealm extends AuthorizingRealm {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 给该用户设置角色，角色信息存在 sys_role 表中取
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        SysUser user = userMapper.selectOne(queryWrapper);
        Set<String> roles = new HashSet<>();

        if(user!=null){

            if(StringUtils.isEmpty(user.getRole())){
                return authorizationInfo;
            }else {
                String[] role_list = user.getRole().split(",");
                for (int i = 0; i < role_list.length; i++) {
                    roles.add(role_list[i]);
                }
                authorizationInfo.setRoles(roles);
                Set<String> permissions = new HashSet<>();
                permissions.add("login");
                // 给该用户设置权限，权限信息存在 t_permission 表中取
                authorizationInfo.setStringPermissions(permissions);
                return authorizationInfo;
            }
        }else{
            Set<String> permissions = new HashSet<>();
            permissions.add("logins");
            // 给该用户设置权限，权限信息存在 t_permission 表中取
            authorizationInfo.setStringPermissions(permissions);

            return authorizationInfo;
        }


    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 根据 Token 获取用户名
        String username = (String) authenticationToken.getPrincipal();
        // 根据用户名从数据库中查询该用户
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        SysUser user = userMapper.selectOne(queryWrapper);
        if(user != null) {
//            // 把当前用户存到 Session 中
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
            ValueOperations<String,SysUser> operations = redisTemplate.opsForValue();
            operations.set(user.getId().toString(),user,5, TimeUnit.HOURS);
//            // 传入用户名和密码进行身份认证，并返回认证信息
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.getPassword(), "systemRealm");
            LogUtils.saveLog(ConstantInfo.LOGIN_IN_LOG_TYPE, "User:"+user.getName() + "login in system", "Last Access Time:" + SecurityUtils.getSubject().getSession().getLastAccessTime());
            return authcInfo;
        } else {
            return null;
        }
    }




    /**
     * 获取权限授权信息，如果缓存中存在，则直接从缓存中获取，否则就重新获取， 登录成功后调用
     */
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            return null;
        }

        AuthorizationInfo info = null;

//        info = (AuthorizationInfo)UserUtils.getCache(UserUtils.CACHE_AUTH_INFO);
//
//        if (info == null) {
//            info = doGetAuthorizationInfo(principals);
//            if (info != null) {
//                UserUtils.putCache(UserUtils.CACHE_AUTH_INFO, info);
//            }
//        }

        return info;
    }
}


