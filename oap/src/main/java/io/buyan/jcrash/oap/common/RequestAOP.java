package io.buyan.jcrash.oap.common;

import io.buyan.jcrash.oap.util.StopWatch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 请求切面
 *
 * @author Pengyu Gan
 * CreateDate 2022/1/25
 */
@Slf4j
@Aspect
@Component
public class RequestAOP {

    @Pointcut("execution(public io.buyan.jcrash.oap.common.JCrashResult *(..))")
    public void execute() {

    }

    /**
     * 切面逻辑
     *
     * @param pjp {@link ProceedingJoinPoint} 切点上下文
     * @return {@link JCrashResult} 请求响应数据模型
     */
    @Around("execute()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        JCrashResult<?> result;
        StopWatch stopWatch = new StopWatch();
        try {
            result = (JCrashResult<?>) pjp.proceed();
            log.info("{} cost time {} ms", pjp.getSignature(), stopWatch.getElapse());
        } catch (Throwable e) { // 处理异常
            result = handlerException(pjp, e);
        }
        return result;
    }

    /**
     * 请求出现异常的处理控制
     *
     * @param pjp {@link ProceedingJoinPoint} 切点上下文
     * @param e   {@link Throwable} 异常信息
     * @return {@link JCrashResult} 请求响应数据模型
     */
    private JCrashResult<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        return JCrashResult.error(e.getMessage());
    }

}
