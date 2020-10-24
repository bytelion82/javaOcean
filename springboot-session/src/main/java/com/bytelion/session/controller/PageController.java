package com.bytelion.session.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.bytelion.session.constants.SessionConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author -> 太傅
 * @date -> 2020/10/24 : 20:45
 * #description    ->
 */
@Controller
@RequestMapping("/page")
public class PageController {

    /**
     * 跳转到 首页
     *
     * @param request 请求
     */
    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        String token = (String) request.getSession().getAttribute(SessionConstant.SESSION_KEY);
        System.out.println(token);
        mv.setViewName("index");
        mv.addObject("token", token);
        return mv;
    }

    /**
     * 跳转到 登录页
     *
     * @param redirect 是否是跳转回来的
     */
    @GetMapping("/login")
    public ModelAndView login(Boolean redirect) {
        ModelAndView mv = new ModelAndView();

        if (ObjectUtil.isNotNull(redirect) && ObjectUtil.equal(true, redirect)) {
            mv.addObject("message", "请先登录！");
        }
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/doLogin")
    public String doLogin(HttpSession session) {
        // 写入session
        session.setAttribute(SessionConstant.SESSION_KEY, IdUtil.fastUUID());

        return "redirect:/page/index";
    }

    @GetMapping("/not")
    @ResponseBody
    public String notLogin(){
        return "ok";
    }
}
