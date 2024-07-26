package by.dre.spring.aop;

import by.dre.spring.validator.UserInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Aspect
@Component
@Order(1)
public class FirstAspect {

    private static final Logger log = LoggerFactory.getLogger(FirstAspect.class);

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {}

    @Pointcut("within(by.dre.spring.service.*Service)")
    public void isServiceLayer() {}

    @Pointcut("this(org.springframework.stereotype.Repository)")
    public void isRepositoryLayer() {}

    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {}

    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelArg() {}

    @Pointcut("isControllerLayer() && @args(by.dre.spring.validator.UserInfo,..)")
    public void hasUserInfoParamAnnotation() {}

    @Pointcut("bean(userService)")
    public void isUserServiceBean() {}

    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {}

    @Pointcut("execution(public * by.dre.spring.service.*Service.findById(*))")
    public void anyServiceFindByIdMethod() {}

    @Pointcut("execution(public * findById(*))")
    public void anyFindByIdMethod() {}

    //Advice

    @Before("anyServiceFindByIdMethod()" +
            "&& args(id)" +
            "&& target(service)" +
            "&& this(serviceProxy)" +
            "&& @within(transactional)")
    public void addLogging(JoinPoint joinPoint, Object id,
                           Object service,
                           Object serviceProxy,
                           Transactional transactional) {
        log.info("Before invoked findById method in class {}, with id {}", service, id);
    }

    @AfterReturning(value = "anyServiceFindByIdMethod()" +
            "&& target(service)", returning = "result")
    public void addLoggingAfterReturning(Object result, Object service) {
        log.info("AfterReturning invoked findById method in class {}, with result {}", service, result);
    }

    @AfterThrowing(value = "anyServiceFindByIdMethod()" +
            "&& target(service)", throwing = "ex")
    public void addLoggingAfterThrowing(Throwable ex, Object service) {
        log.info("Throwing invoked findById method in class {}, with throwing {}", service, ex);
    }

    @After("anyServiceFindByIdMethod()" +
            "&& target(service)")
    public void addLoggingAfter(Object service) {
        log.info("Throwing invoked findById method in class {}", service);
    }
}
