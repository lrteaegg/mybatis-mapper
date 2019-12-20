package com.felton.mybatismapper.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author LiuRui
 * @version 1.0
 * @date 2019/12/20 13:15
 * @description
 */
@Aspect
public class MathAspects {

    @Pointcut("execution(public * com.felton.mybatismapper.util.*.*(..))")
    public void pointCut(){}

    // 方法执行前
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("除法运行...参数：{" +
                Arrays.asList(joinPoint.getArgs())+"}");
    }

    // 方法执行后
    @After("pointCut()")
    public void logEnd (JoinPoint joinPoint) {
        System.out.println("除法结束.." + joinPoint.getSignature().getName());
    }

    @AfterReturning("pointCut()")
    public void logAfterReturn(JoinPoint joinPoint) {
        System.out.println("返回结果结束后.."+joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {
        System.out.println("异常....异常信息： {" + e + "}");
    }

    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知...  开始");
        // 执行方法
        Object obj = joinPoint.proceed();
        System.out.println("运行结果："+obj);
        // 原方法执行结束，打印这行日志
        System.out.println("环绕通知.. 结束");
        return obj;
    }

}
