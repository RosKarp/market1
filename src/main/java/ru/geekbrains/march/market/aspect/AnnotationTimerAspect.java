package ru.geekbrains.march.market.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AnnotationTimerAspect {

    @Pointcut("@within(ru.geekbrains.march.market.aspect.Timer)")
    public void classesAnnotatedWithTimer() {

    }
    @Pointcut("@annotation(Timer)")
    public void methodAnnotatedWithTimer() {

    }
    @Pointcut("classesAnnotatedWithTimer() || methodAnnotatedWithTimer()")
    public void targetTimer() {

    }

    @Around("targetTimer()")
    public Object aroundAnnotatedTimer(ProceedingJoinPoint pjp) throws Throwable {

            Long start = System.currentTimeMillis();
             Object result = pjp.proceed();
            Long finish = System.currentTimeMillis();
            Long executionTime = finish - start;

            String className = pjp.getTarget().getClass().getName();
            String methodName = pjp.getSignature().getName();

            log.info("Время исполнения {}#{} : {} мс", className, methodName, executionTime);
            return result;
        }
    }
