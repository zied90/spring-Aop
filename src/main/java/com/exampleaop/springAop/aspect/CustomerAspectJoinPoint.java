package com.exampleaop.springAop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class CustomerAspectJoinPoint {

    @Before("args(firstName, id)")
    public void logStringArguments(String firstName, long id) {
        System.out.println("Passed Arguments: [" + "firstName = " + firstName + "; id = " + id + "]");
    }

    @Before("execution(* com.exampleaop.springAop.service.CustomerAdvanceService.setCustomerFirstName(..))")
    public void logAdvice(JoinPoint joinPoint) {
        System.out.println("Before running Advice on " + joinPoint.toString());
        System.out.println("Display Passed Arguments: " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("execution(* com.exampleaop.springAop.service.CustomerAdvanceService.findAllCustomers())")
    public Object logAround(ProceedingJoinPoint joinPoint) {
        System.out.println("This is LogAdvice BEFORE excuting Method");

        Object returnObj = null;

        try {
            returnObj = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        System.out.println("This is LogAdvice AFTER excuting Method");

        return returnObj;
    }
}
