package com.longmaominsu.project.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longmaominsu.project.common.persistence.BaseController;
import com.longmaominsu.project.common.utils.ConstantInfo;
import com.longmaominsu.project.common.utils.ErrorCodeUtil;
import com.longmaominsu.project.common.utils.ResponseUtil;
import com.longmaominsu.project.modules.sys.entity.SysUser;
import com.longmaominsu.project.modules.sys.service.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liupeng on 2019/9/20 10:00 PM.
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController{

    @Autowired
    UserMapper userMapper;
    /**
     * 用户登录接口
     * @param user user
     * @param request request
     * @return string
     */
    @PostMapping("login")
    @ResponseBody
    public Map login(@RequestBody SysUser user, HttpServletRequest request) {

        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        // 获取 subject 认证主体
        Subject subject = SecurityUtils.getSubject();
        try{
            // 开始认证，这一步会跳到我们自定义的 Realm 中
            subject.login(token);
            QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", user.getName());
            SysUser user1 = userMapper.selectOne(queryWrapper);
            request.getSession().setAttribute("user", user1);

            return ResponseUtil.success(ConstantInfo.SUCCESS_LOGIN,user);
        }catch(UnknownAccountException e1){
            return ResponseUtil.fail(ErrorCodeUtil.不存在该用户);
        }catch (AuthenticationException e2){

            return ResponseUtil.fail(ErrorCodeUtil.用户密码校验失败);
        }catch (Exception exception){
            exception.printStackTrace();

            return ResponseUtil.fail(ErrorCodeUtil.未知系统错误);
        }
    }

    @PostMapping("test")
    @ResponseBody
    @RequiresPermissions("login")
    public Map test(@RequestBody SysUser user,HttpServletRequest request){
        Map response = new HashMap<>();
        return response;
    }

    @GetMapping("unauthorized")
    @ResponseBody
    public Map unauthorized(HttpServletRequest request){
        return ResponseUtil.fail(ErrorCodeUtil.用户未拥有此权限);
    }

    @GetMapping("gotologin")
    @ResponseBody
    public Map goToLogin(HttpServletRequest request){
        return ResponseUtil.fail(ErrorCodeUtil.用户未登录);
    }



}
