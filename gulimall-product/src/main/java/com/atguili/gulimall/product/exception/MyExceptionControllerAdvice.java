package com.atguili.gulimall.product.exception;

import com.atguili.common.exception.BizCodeEnum;
import com.atguili.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(basePackages = "com.atguili.gulimall.product.controller")
public class MyExceptionControllerAdvice {

    // 方法参数无效异常   MethodArgumentNotValidException.class
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e){
        log.error("数出错:{}, 异常类型:{}", e.getMessage(), e.getClass());
        Map<String, String> map = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(), BizCodeEnum.VALID_EXCEPTION.getMsg()).put("data", map);
    }

//    @ExceptionHandler(value = Throwable.class)
//    public R handleException(Throwable throwable){
//        log.error("未知异常{},异常类型{}",throwable.getMessage(),throwable.getClass());
//        return R.error(BizCodeEnum.UNKNOW_EXEPTION.getCode(), BizCodeEnum.UNKNOW_EXEPTION.getMsg());
//    }
}
