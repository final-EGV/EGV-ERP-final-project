package org.erp.egv.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingMethodInvokeAspect {

	@Before("execution(* org.erp.egv.theater..*DAO.*(..))"
			+ " || execution(* org.erp.egv.theater..*DAO.*(..))")
	public void loggingMethodInvoke(JoinPoint jp) {
		
		Signature signature = jp.getSignature();
		
		Class<? extends Object> targetObjectType = jp.getTarget().getClass();
		Logger logger = LoggerFactory.getLogger(targetObjectType);
		
		logger.info("##### [CALLING] : {}.{}() with arguments {}",
				signature.getDeclaringTypeName(),
				signature.getName(),
				jp.getArgs());
	}
}
