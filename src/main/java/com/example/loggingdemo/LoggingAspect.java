package com.example.loggingdemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.loggingdemo.StudentService.*(..))")
    public Object logExecutionDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        // Format arguments
        StringBuilder argsStr = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            argsStr.append(args[i]);
            if (i < args.length - 1) argsStr.append(", ");
        }

        logger.info("➡️ [AOP] {}({})", methodName, argsStr);

        long start = System.currentTimeMillis();

        Object result = null;
        try {
            result = joinPoint.proceed(); // Run the method
            return result;
        } finally {
            long end = System.currentTimeMillis();
            logger.info("⬅️ [AOP] {} returned: {} ({} ms)", methodName,
                    result == null ? "void" : result.toString(),
                    end - start);
        }
    }
}
