package com.example.sb_assign_16_05_23.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Define an around advice for all methods in com.example package
    @Around("execution( * com.example.sb_assign_16_05_23.*.*.*(..))")
//    @Around("execution(* com.example.sb_assign_16_05_23.*()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
// Log before method execution
        logger.info("Before method: " + joinPoint.getSignature().getName());
// Execute the method and get the result
        Object result = joinPoint.proceed();
// Log after method execution
        logger.info("After method: " + joinPoint.getSignature().getName());
// Return the result
        return result;
    }
}