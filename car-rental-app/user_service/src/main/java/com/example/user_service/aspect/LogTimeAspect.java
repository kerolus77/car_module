package com.example.user_service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogTimeAspect {
    Logger log = LoggerFactory.getLogger(LogTimeAspect.class);
    @Around(value = "execution(* com.example.user_service.service..*(..))")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("KPI:");
        sb.append("[").append(joinPoint.getKind()).append("]\tfor: ").append(joinPoint.getSignature())
                .append("\twithArgs: ").append("(").append(")");
        sb.append("\ttook:");
        Object returnValue =joinPoint.proceed();
        log.info(sb.append(System.currentTimeMillis() - startTime).append("ms.").toString());

        return returnValue;
    }
}
