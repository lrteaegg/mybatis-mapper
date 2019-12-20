package com.felton.mybatismapper.aspect;

import com.felton.mybatismapper.annotation.LogAnno;
import org.apache.tomcat.jni.Proc;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author LiuRui
 * @version 1.0
 * @date 2019/12/20 14:18
 * @description
 */

@Component
@Aspect
public class LogAopAspect {

    // 定义切点
    @Pointcut("@annotation(com.felton.mybatismapper.annotation.LogAnno)")
    public void logPointCut(){}

    @Around("logPointCut()")
    public Object printMethodParams(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = getMethod(joinPoint);
        Object object = null;

        if (method != null && method.isAnnotationPresent(LogAnno.class)) {
            LogAnno anno = method.getAnnotation(LogAnno.class);
            String operateType = anno.operateType();
             object = joinPoint.proceed();
            System.out.println("log show :" + object);
        }
        return object;
    }

    /**
     * 返回切面方法
     * @param joinPoint
     * @return
     */
    private Method getMethod(ProceedingJoinPoint joinPoint) {
//        Object target = joinPoint.getTarget();
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
//        Object[] params = method.getParameters();
      /*  try {
            method = target.getClass().getMethod(method.getName(), method.getParameterTypes());
//            params = method.getParameters();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }*/
        return method;
    }

    /**
     * 放回切面方法参数
     * @param joinPoint
     * @return
     */
    private Object[] getArgs(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        return args;
    }

}
