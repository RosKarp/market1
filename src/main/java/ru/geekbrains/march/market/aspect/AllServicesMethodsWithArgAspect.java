package ru.geekbrains.march.market.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AllServicesMethodsWithArgAspect {

    @Before("execution(* ru.geekbrains.march.market.services.*.*(..))")     // ограничился папкой services
    public void beforeServicesMethods(JoinPoint jp) {
            String className = jp.getTarget().getClass().getName();
            String methodName = jp.getSignature().getName();
            if (jp.getArgs().length == 0) {
                log.info("Вызов метода {}#{} без аргументов", className, methodName);
            } else {
                Object arg = jp.getArgs()[0];
                log.info("Вызов метода {}#{} с аргументом: {}", className, methodName, arg);
            }
    }
}
