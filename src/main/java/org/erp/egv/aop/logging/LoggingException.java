package org.erp.egv.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingException {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingException.class);
	
	/**
	 * A join point is in the exception layer for all the methods defined in the
	 * project.
	 */
	@Pointcut("within(org.erp.egv..*)")
	public void inExceptionLayer() {}

	@AfterThrowing(pointcut = "inExceptionLayer()",
			throwing = "e")
	public void loggingException(JoinPoint jp, Throwable e) {
		
		Signature signature = jp.getSignature();
		
		LOGGER.error("##### [ERROR] --> Exception in {}.{}() with cause = {}",
				signature.getDeclaringTypeName(),
				signature.getName(),
				e.getCause() != null ? e.getCause() : "NULL");
	}
}
