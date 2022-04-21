package org.erp.egv.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingException {

	@AfterThrowing(pointcut = "execution(* org.erp.egv.theater..*.*(..))",
					throwing = "e")
	public void loggingException(JoinPoint jp, Throwable e) {
		
		Signature signature = jp.getSignature();
		
		Class<? extends Object> targetObjectType = jp.getTarget().getClass();
		Logger logger = LoggerFactory.getLogger(targetObjectType);
		
		logger.error("##### [ERROR] : Exception in {}.{}() with cause = {}",
				signature.getDeclaringTypeName(),
				signature.getName(),
				e.getCause() != null ? e.getCause() : "NULL");
	}
}
