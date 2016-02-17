package com.github.dreambrother.examples.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TestAspect {

    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    @Around("execution(* com.github.dreambrother.examples.aspect.TestService.*(..))")
    public void aroundAnyMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        logger.info("Before executing '{}', with args {}", methodName, pjp.getArgs());
        try {
            pjp.proceed();
        } catch (Exception ex) {
            logger.info("After throwing '{}' {}", methodName, ex.getMessage());
        } finally {
            logger.info("After executing '{}'", methodName);
        }
    }
}
