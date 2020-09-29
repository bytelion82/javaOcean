package com.bytelion.springbootlogback.controller;

import com.bytelion.springbootlogback.annotation.LogRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 太傅
 * @version V1.0
 * @package com.bytelion.springbootlogback.controller
 * @description
 * @date: Created in 2020/9/29 15:33
 * @copyright Copyright (c) 2020/9/29
 */
@RestController
public class WebController {


    @LogRecord("log接口")
    @GetMapping("/log")
    public String testLog(String name){
        return name;
    }
}
