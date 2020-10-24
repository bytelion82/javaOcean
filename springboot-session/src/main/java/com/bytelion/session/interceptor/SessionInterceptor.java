package com.bytelion.session.interceptor;

import com.bytelion.session.constants.SessionConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author -> 太傅
 * @date -> 2020/10/24 : 20:32
 * #description    ->
 */
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionConstant.SESSION_KEY)!=null) {
            return true;
        }
        String url="/page/login?redirect=true";
        System.out.println("request.getContextPath():"+request.getContextPath());
        response.sendRedirect(request.getContextPath()+url);
        return false;
    }
}
