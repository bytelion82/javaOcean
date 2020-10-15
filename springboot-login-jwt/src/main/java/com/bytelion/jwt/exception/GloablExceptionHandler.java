package com.bytelion.jwt.exception;


import com.fasterxml.jackson.databind.util.JSONPObject;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GloablExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e){
        System.out.println(e.toString());
        String message = e.getMessage();
        if (message==null||message.equals("")) {
            message="服务器出错";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        return jsonObject;
    }
}
