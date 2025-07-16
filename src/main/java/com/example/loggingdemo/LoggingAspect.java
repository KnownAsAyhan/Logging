package com.example.loggingdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect  // Mark this class as an aspect
@Component  // Make it a Spring bean
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Run BEFORE any method in StudentService
    @Before("execution(* com.example.loggingdemo.StudentService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("🚀 [AOP] Starting: " + joinPoint.getSignature().getName());
    }

    // Run AFTER method successfully returns
    @AfterReturning("execution(* com.example.loggingdemo.StudentService.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("✅ [AOP] Finished: " + joinPoint.getSignature().getName());
    }

    // Run if method throws an exception
    @AfterThrowing(pointcut = "execution(* com.example.loggingdemo.StudentService.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error("❌ [AOP] Exception in: " + joinPoint.getSignature().getName());
        logger.error("❗ [AOP] Error: " + ex.getMessage());
    }
    @Around("execution(* com.example.loggingdemo.StudentService.*(..))")
    public Object logExecutionTime(org.aspectj.lang.ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis(); // Start timer

        Object result = joinPoint.proceed(); // Run the actual method

        long end = System.currentTimeMillis(); // End timer
        long duration = end - start;

        logger.info("⏱️ [AOP] {} executed in {} ms", joinPoint.getSignature().getName(), duration);
        return result;
    }


}
