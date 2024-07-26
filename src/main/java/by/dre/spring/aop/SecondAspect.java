package by.dre.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(2)
public class SecondAspect {
    @Around("by.dre.spring.aop.FirstAspect.anyServiceFindByIdMethod() && target(service)" +
            "&& args(id)")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object service, Object id) {
        log.info("AROUND before - invoked findById method in class {}, with id {}", service, id);

        try {
            Object result = joinPoint.proceed();
            log.info("AROUND AfterReturning invoked findById method in class {}, with result {}", service, result);
            return result;
        } catch (Throwable ex) {
            log.info("AROUND Throwing invoked findById method in class {}, exception {}: {}", service, ex.getClass(), ex.getMessage());
            return ex;
        } finally {
            log.info("AROUND after (finally) - invoked findById method in class {}", service);
        }
    }
}
