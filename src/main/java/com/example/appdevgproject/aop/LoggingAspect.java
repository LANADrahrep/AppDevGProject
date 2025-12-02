package com.example.appdevgproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    // 1 - controllers
    @Pointcut("within(com.example.appdevgproject.controller..*)")
    public void controllerMethods() {}

    // 2 - services
    @Pointcut("within(com.example.appdevgproject.service..*)")
    public void serviceMethods() {}

    // 3 - repositories
    @Pointcut("execution(* com.example.appdevgproject.repository..*(..))")
    public void repositoryMethods() {}

    @Before("controllerMethods()")
    public void logControllerCall(JoinPoint joinPoint) {
        log.info("Controller call: {}.{}({})",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @Before("serviceMethods()")
    public void logServiceCall(JoinPoint joinPoint) {
        log.info("Service call: {}.{}({})",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "repositoryMethods()", returning = "result")
    public void logRepositoryReturn(JoinPoint joinPoint, Object result) {
        log.info("Repository call: {}.{}(..) returned {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                result);
    }
}