package com.yjq.programmer.exception;

import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-16 22:23
 */

/**
 * 运行时触发异常捕获
 */
@ControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseVo<Boolean> handle(RuntimeException e) {
        e.printStackTrace();
        return ResponseVo.errorByMsg(CodeMsg.SYSTEM_ERROR); //如果要使用自定义的throw new exception("...")的异常消息  要用e.getCause().getMessage()
    }

}
