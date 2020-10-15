package com.bytelion.jwt.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.bytelion.jwt.entity.User;
import com.bytelion.jwt.annotation.PassToken;
import com.bytelion.jwt.annotation.UserLoginToken;
import com.bytelion.jwt.service.IUserService;
import com.bytelion.jwt.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 在所有请求前
 * 检测是否带有token
 */

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        log.info("token:{}", token);
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        log.info("method:{}", method);
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 检测是否带有USerLoginToken 注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                //执行认证
                if (token == null) {
                    throw new RuntimeException("请求头token参数为空 ,login try again");
                }
            }
            Map<String, Claim> claimMap = JwtUtil.verifyToken(token);
            log.info("claimMap:" + claimMap.toString());
            log.info("claimMap.get(\"id\"):" + claimMap.get("id").toString());
            //查询是否有该用户
            User user = userService.findUserById(claimMap.get("id").toString());
            if (user == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }
            return true;

        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("后处理回调方法，实现处理器的后处理（DispatcherServlet进行视图返回渲染之前进行调用）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中");
    }
}
