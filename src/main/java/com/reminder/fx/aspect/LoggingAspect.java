package com.reminder.fx.aspect;

import java.util.Arrays;
import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    
    @Around("execution(* com.reminder.fx.services.*.*(..))")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] methodArgs = joinPoint.getArgs();

        log.info("---> Iniciando ejecución del método: {} con argumentos {}", methodName, Arrays.toString(methodArgs));

        Long start = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.error("--> Excepción en el método: {} - Error: {}", methodName, e.getMessage());
            throw e;
        }
        Long duration = System.currentTimeMillis() - start;
        if (Objects.isNull(result)) {
            log.info("--> Finalizando ejecución del método: {} - Time: {} ms - Resultado: null", methodName, duration);
        } else {
            log.info("--> Finalizando ejecución del método: {} - Time: {} ms - Resultado: {}", methodName, duration,
                    result);
        }
        return result;
    }

}
