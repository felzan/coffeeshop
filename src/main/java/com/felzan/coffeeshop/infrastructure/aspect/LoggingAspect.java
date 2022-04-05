package com.felzan.coffeeshop.infrastructure.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
//@Aspect
@Component
public class LoggingAspect {

//  @Around("execution(* com.felzan.coffeeshop.adapters.web..*(..))")
//  public Object logAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//
//    //Get intercepted method details
//    String className = methodSignature.getDeclaringType().getSimpleName();
//    String methodName = methodSignature.getName();
//
//    final StopWatch stopWatch = new StopWatch();
//
//    //Measure method execution time
//    stopWatch.start();
//    Object result = proceedingJoinPoint.proceed();
//    stopWatch.stop();
//
//    //Log method execution time
//    log.info("Execution time of " + className + "." + methodName + " "
//        + ":: " + stopWatch.getTotalTimeMillis() + " ms");
//
//    return result;
//  }


}
