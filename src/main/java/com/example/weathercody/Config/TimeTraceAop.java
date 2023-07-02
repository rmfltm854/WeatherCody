package com.example.weathercody.Config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
//spring AOP
@Aspect
@Component
@Slf4j
public class TimeTraceAop {

    @Around("execution(* com.example.weathercody..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        log.warn("START: " + joinPoint.toString());

        try{
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            log.warn("END: " + joinPoint.proceed() + " "+ timeMs + "ms");
        }
    }
}
