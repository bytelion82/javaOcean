package com.bytelion.springbootlogback.aspect;

import com.bytelion.springbootlogback.annotation.LogRecord;
import com.bytelion.springbootlogback.entity.SysLog;
import com.bytelion.springbootlogback.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author 太傅
 * @version V1.0
 * @package com.bytelion.springbootlogback.aspect
 * @description 将带有@LOG 注解的方法记录日志，写入到MySQL数据库中/记录到日志文件
 * @date: Created in 2020/9/29 15:11
 * @copyright Copyright (c) 2020/9/29
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    @Pointcut("@annotation(com.bytelion.springbootlogback.annotation.LogRecord)")
    public void pointcut(){

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point){
        long beginTime = System.currentTimeMillis();
        Object proceed=null;
        try {
            proceed = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long time =System.currentTimeMillis()-beginTime;
        recordLog(point,time);
        return proceed;

    }

    private void recordLog(ProceedingJoinPoint joinPoint,long time){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        //获取方法上是否带有自定义的Log 注解
        LogRecord logAnnotation = method.getAnnotation(LogRecord.class);
        if (logAnnotation!=null){
            // 注解上的描述
            sysLog.setOperation(logAnnotation.value());
        }
        // 请求的方法名
        String className = joinPoint.getClass().getName();
        String name = signature.getClass().getName();

        String methodName = signature.getName();
        sysLog.setMethod(className+"."+methodName+"()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args !=null && paramNames != null){
            String params ="";
            for (int i = 0; i < args.length; i++) {
                params+= " "+ paramNames[i]+": "+ args[i];
            }
            sysLog.setParams(params);
        }

        //获取request

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        sysLog.setIp(IpUtil.getIpAddr(request));
        sysLog.setUsername("person");
        sysLog.setCreateTime(LocalDateTime.now());
        sysLog.setTime((int) time);
        // 系统保存日志到MySQL
        //boolean save = sysLogService.save(sysLog);
        log.info("切面日志记录："+String.valueOf(sysLog));


    }

}
