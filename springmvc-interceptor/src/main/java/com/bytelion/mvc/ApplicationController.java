package com.bytelion.mvc;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  * @author 太傅
 *  * @date 2020-09-27 17:20
 */
@Controller
public class ApplicationController {

    @RequestMapping("/test")
    @ResponseBody
    public String testInterceptor(){
        System.out.println("testInterceptor执行了...");
        return "success";
    }
}
