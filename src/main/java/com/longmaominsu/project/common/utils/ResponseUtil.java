package com.longmaominsu.project.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liupeng on 2019/10/5 10:50 PM.
 */
public final class ResponseUtil {

    private ResponseUtil(){}





    public static Map success(String message,Object data){
        Map response = new HashMap<>();
        response.put(ConstantInfo.RESULT,ConstantInfo.SUCCESS);
        response.put(ConstantInfo.MESSAGE,message);
        response.put(ConstantInfo.ERRORCODE,ConstantInfo.SUCCESS_ERROR_CODE);
        response.put(ConstantInfo.TIMESTAMP,System.currentTimeMillis());
        if(data!=null){
            response.put(ConstantInfo.DATA,data);
        }else{
            response.put(ConstantInfo.DATA,"");
        }
        return response;

    }

    public static Map fail(int errorCode){
        ErrorCodeUtil errorCodeUtil = new ErrorCodeUtil();
        Map response = new HashMap<>();
        response.put(ConstantInfo.RESULT,ConstantInfo.FAIL);
        response.put(ConstantInfo.MESSAGE,errorCodeUtil.getErrorMessage(errorCode));
        response.put(ConstantInfo.ERRORCODE,errorCode);
        response.put(ConstantInfo.TIMESTAMP,System.currentTimeMillis());
        response.put(ConstantInfo.DATA,ConstantInfo.FAIL);
        return response;

    }
}
