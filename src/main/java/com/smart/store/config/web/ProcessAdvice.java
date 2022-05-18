package com.smart.store.config.web;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Slf4j
@Component
public class ProcessAdvice {

    @Pointcut("execution(* com.smart.store.controller.v1.*.*(..))")
    public void controllerAdvice() {}

    @Around("controllerAdvice()")
    public Object controllerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime start = LocalDateTime.now();
        log.info("api: {} start, params: {}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getArgs());
        Object o = joinPoint.proceed();
        LocalDateTime end = LocalDateTime.now();
        log.info("api: {} end, cost: {} ms, response: {}", joinPoint.getTarget().getClass().getSimpleName(), Duration.between(start, end).toMillis(), o == null ? "" : o.toString());
        return o;
    }
}
