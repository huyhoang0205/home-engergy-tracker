package com.huyhoang25.user_service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ExecutionTImeAspect {

    @Pointcut("execution(* com.huyhoang25.user_service.controller.*.*(..))")
    public void controllerMethod() {
    }

    @Around("controllerMethod()")
    public Object measureExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        try {
            return pjp.proceed();
        } finally {
            long end = System.nanoTime();
            long elapsedNs = end - start;
            long elapsedMs = elapsedNs / 1000000;
            String signature = pjp.getSignature().toShortString();
            log.info("Controller method {} executed in {} ms", signature, elapsedMs);
        }
    }
}
