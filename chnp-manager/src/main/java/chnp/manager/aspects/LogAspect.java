package chnp.manager.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Around(value = "execution(public String chnp.manager.mvc.controller.*.*(..))")
    public Object logController(ProceedingJoinPoint pjp) {
        try {
            return pjp.proceed();
        }catch (Throwable thr) {
            log.error("", thr);
            return null;
        }
    }

}