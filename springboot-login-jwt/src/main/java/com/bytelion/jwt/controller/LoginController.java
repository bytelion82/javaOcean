package com.bytelion.jwt.controller;

import com.bytelion.jwt.entity.User;
import com.bytelion.jwt.annotation.PassToken;
import com.bytelion.jwt.annotation.UserLoginToken;
import com.bytelion.jwt.service.IUserService;
import com.bytelion.jwt.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    IUserService userService;

    /**
     * 通过这个接口获取token值
     * @return
     */
    @PassToken
    @RequestMapping("/sign")
    public Object login(){
        User user = new User("1","byte","byte");
        JSONObject jsonObject = new JSONObject();
        User userForBase = userService.findByUsername(user);
        if (userForBase==null) {
            jsonObject.put("mes","login failed , not exist");
            return jsonObject;
        }else {
            if (!userForBase.getPassword().equals(user.getPassword())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = JwtUtil.createToken(user);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }

    @GetMapping("/hello")
    public String hello(){
        return "你无须验证";
    }

}
