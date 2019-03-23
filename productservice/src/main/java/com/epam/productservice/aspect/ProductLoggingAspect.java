package com.epam.productservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductLoggingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Before("execution(* com.epam.productservice.*.*.*(..))")
    public void before(JoinPoint joinPoint) {
        logger.info("Before method calling: ", joinPoint.getArgs().toString());
    }

    @AfterReturning(value = "execution(* com.epam.productservice.*.*.*(..))",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("{} returned with value {}", joinPoint, result);
    }

    @Around("execution(* com.epam.productservice.*.*(..))")
    public Object around(ProceedingJoinPoint theProceedingJoinPoint)
            throws Throwable
    {
        try
        { // let's execute the method
            logger.info(" Excecuting the method {}", theProceedingJoinPoint);
             Object result = theProceedingJoinPoint.proceed();
             return result;
        } catch (Exception exc)
        {
            // log exception
            logger.error("@Around advice: We have a problem " + exc);
            // rethrow it
            throw exc;
        }
    }
}
