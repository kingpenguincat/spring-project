package com.longmaominsu.project.modules.sys.controller;

import com.longmaominsu.project.modules.sys.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liupeng on 2019/9/22 8:17 PM.
 */
@Controller
@RequestMapping("/sys")
public class TestController {
    /**
     * 用户登录接口
     * @param user user
     * @param request request
     * @return string
     */
    @PostMapping("/test")
    @ResponseBody
    public Map login(@RequestBody SysUser user, HttpServletRequest request) {
        Map response = new HashMap<>();
        return response;
    }
}
