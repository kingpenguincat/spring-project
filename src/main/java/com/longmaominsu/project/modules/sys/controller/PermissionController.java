package com.longmaominsu.project.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.longmaominsu.project.common.persistence.BaseController;
import com.longmaominsu.project.common.utils.ConstantInfo;
import com.longmaominsu.project.common.utils.ErrorCodeUtil;
import com.longmaominsu.project.common.utils.ResponseUtil;
import com.longmaominsu.project.modules.sys.entity.SysPermission;
import com.longmaominsu.project.modules.sys.entity.SysUser;
import com.longmaominsu.project.modules.sys.service.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by liupeng on 2019/10/6 3:04 PM.
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController{

    @Autowired
    PermissionMapper permissionMapper;

    /**
     * 用户授权接口
     * @param sysPermission syspermission
     * @param request request
     * @return string
     */
    @PostMapping("save")
    @ResponseBody
    public Map save(@RequestBody SysPermission sysPermission, HttpServletRequest request) {

        if(permissionMapper.selectById(sysPermission.getId())!=null && sysPermission.getId()!=null){
            UpdateWrapper<SysPermission> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("value",sysPermission.getValue());
            updateWrapper.set("status",sysPermission.getStatus());
            updateWrapper.eq("id",sysPermission.getId());
            permissionMapper.update(sysPermission, updateWrapper);
        }else{
            permissionMapper.insert(sysPermission);
        }


        return ResponseUtil.success(ConstantInfo.SUCCESS_SAVE,sysPermission);
    }

    @PostMapping("view")
    @ResponseBody
    public Map view(@RequestBody SysPermission sysPermission, HttpServletRequest request) {

        if(permissionMapper.selectById(sysPermission.getId())!=null && sysPermission.getId()!=null){
            sysPermission = permissionMapper.selectById(sysPermission.getId());
        }else{
            return ResponseUtil.fail(ErrorCodeUtil.不存在该用户);
        }


        return ResponseUtil.success(ConstantInfo.SUCCESS_SAVE,sysPermission);
    }


}
