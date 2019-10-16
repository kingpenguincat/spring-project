package com.longmaominsu.project.common.utils;

/**
 * Created by liupeng on 2019/10/5 11:08 PM.
 */
public class ErrorCodeUtil {
    public static final int 未知系统错误 = -1;
    public static final int 用户未拥有此权限 = 1001;
    public static final int 用户密码校验失败 = 1002;
    public static final int 不存在该用户 = 1003;
    public static final int 用户未登录 = 1004;

    public String getErrorMessage(int errorCode){
        String errorMessage = "";

        switch (errorCode){
            case 1001:
                errorMessage="用户未获得该授权";
                break;
            case 1002:
                errorMessage="用户密码校验失败";
                break;
            case 1003:
                errorMessage="不存在该用户";
                break;
            case 1004:
                errorMessage="用户未登录";
                break;
            default:
                errorMessage="未知系统错误";
        }




        return errorMessage;
    }
}
