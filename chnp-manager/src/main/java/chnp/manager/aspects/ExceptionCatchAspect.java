package chnp.manager.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionCatchAspect {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Around(value = "execution(public * chnp.manager.controller.*.*(..))")
	public Object onThrowing(ProceedingJoinPoint pjp) {
		try {
			return pjp.proceed();
		}catch (Throwable thr) {
			log.error("", thr);
			// TODO: 统一返回
			return null;
		}
	}

}