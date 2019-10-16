package com.longmaominsu.project.common.global;

import com.longmaominsu.project.common.utils.ErrorCodeUtil;
import com.longmaominsu.project.common.utils.ResponseUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liupeng on 2019/10/5 10:46 PM.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Map defaultExceptionHandler(HttpServletRequest req,Exception e){
        return ResponseUtil.fail(ErrorCodeUtil.用户未拥有此权限);
    }

}
