package com.example.jwt.exception;

import com.example.jwt.utils.SzpJsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_EXTENDED;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler  {

    /**
     * @ExceptionHandler 表示拦截异常
     *    @ControllerAdvice 是 controller 的一个辅助类，最常用的就是作为全局异常处理的切面类
     *    @ControllerAdvice 可以指定扫描范围
     *    @ControllerAdvice 约定了几种可行的返回值，如果是直接返回 model 类的话，需要使用 @ResponseBody 进行 json 转换
     *    返回 String，表示跳到某个 view
     *    返回 modelAndView
     *    返回 model + @ResponseBody
     */
    /**
     * 返回json字符串的 还有返回modeAndView的
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public SzpJsonResult exceptionHandler(){
        Map<String, Object> errorResultMap = new HashMap<String, Object>();
        errorResultMap.put("errorCode", "500");
        errorResultMap.put("errorMsg", "系統错误!");
        return SzpJsonResult.ok(errorResultMap);
    }
}
