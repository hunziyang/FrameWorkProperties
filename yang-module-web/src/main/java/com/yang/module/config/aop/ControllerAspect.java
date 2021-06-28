package com.yang.module.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * 此组件为切面配置
 * 除环绕通知之外的执行顺序
 * 无环绕:前置通知--后置通知--返回通知/异常通知
 * 有环绕:前置通知--环绕前置通知--环绕返回/环绕异常--环绕后置--后置通知--返回通知/异常通知
 */
@Component
@Aspect
@Slf4j
public class ControllerAspect {

    /**
     * 确定切点
     */
    @Pointcut(value = "execution(* com.yang.module.controller.TeacherController.pageSelect(..))")
    private void pointCut() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before(value = "pointCut()")
    public void beforeAspect(JoinPoint joinPoint) {
        // 获取方法相应信息
        Signature signature = joinPoint.getSignature();
        log.info("前置通知:" + signature.getName() + "的通知");
        log.info("aop-类名:" + signature.getDeclaringType().getSimpleName());
        log.info("aop-全类名:" + signature.getDeclaringTypeName());
        log.info("aop-方法的访问修饰符:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        log.info("aop-被代理对象:" + joinPoint.getTarget());
        log.info("aop-代理类:" + joinPoint.getThis());
    }

    /**
     * 后置通知
     *
     * @param joinPoint
     */
    @After(value = "pointCut()")
    public void afterAspect(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("后置通知:" + signature.getName() + "的通知");
    }

    /**
     * 返回通知
     *
     * @param joinPoint
     * @param o
     */
    @AfterReturning(value = "pointCut()", returning = "o")
    public void returningAspect(JoinPoint joinPoint, Object o) {
        Signature signature = joinPoint.getSignature();
        log.info("返回通知:" + signature.getName() + "的通知");
        log.info("返回值:" + o);
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void throwingAspect(JoinPoint joinPoint, Exception e) {
        Signature signature = joinPoint.getSignature();
        log.info("返回通知:" + signature.getName() + "的通知");
        log.error("err:" + e.getMessage());
    }

    @Around(value = "pointCut()")
    public Object aroundAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        log.info("环绕前置通知:" + signature.getName() + "的通知");
        try {
            Object proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            log.info("环绕返回通知:" + signature.getName() + "的通知，返回值:"+proceed);
            return proceed;
        } catch (Throwable throwable) {
            log.info("环绕异常通知:" + signature.getName() + "的通知，异常:"+throwable.getMessage());
            throw  throwable;
        }finally {
            log.info("环绕后置通知:" + signature.getName() + "的通知");
        }
    }
}
