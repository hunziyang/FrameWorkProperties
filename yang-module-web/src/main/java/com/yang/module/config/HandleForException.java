package com.yang.module.config;

import com.yang.module.common.Result;
import com.yang.module.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 对Exception进行监听
 */
@RestControllerAdvice
@Slf4j
public class HandleForException {

    /**
     * 将异常返回统一
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("err:", e);
        return Result.error(ResultCode.FAILED);
    }
}
