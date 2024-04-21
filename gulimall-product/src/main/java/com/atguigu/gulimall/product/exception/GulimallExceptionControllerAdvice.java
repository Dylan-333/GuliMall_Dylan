package com.atguigu.gulimall.product.exception;

import com.atguigu.common.exception.BizCodeEnum;
import com.atguigu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/*
*   集中处理所有异常
*/
//@ResponseBody
//@ControllerAdvice(basePackages = "com.atguigu.gulimall.product.controller")
@Slf4j
@RestControllerAdvice(basePackages = "com.atguigu.gulimall.product.controller")
public class GulimallExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleVailidException(MethodArgumentNotValidException e) {
        log.error("数据校验异常{},异常类型:{}",e.getMessage(),e.getClass());

        HashMap<String, String> data = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((error) -> {
            data.put(error.getField(), error.getDefaultMessage());
        });
        return R.error(400,"数据校验出现问题").put("data",data);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {

        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(), BizCodeEnum.UNKNOW_EXCEPTION.getMessage());
    }
}
