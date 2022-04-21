package org.erp.egv.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingMethodTermination {

	@AfterReturning(pointcut = "execution(* org.erp.egv.theater..*Service.*(..))"
								+ " || execution(* org.erp.egv.theater..*DAO.*(..))",
					returning = "result")
	public void loggingMethodTermination(JoinPoint jp, Object result) {
		
		Signature signature = jp.getSignature();
		
		Class<? extends Object> targetObjectType = jp.getTarget().getClass();
		Logger logger = LoggerFactory.getLogger(targetObjectType);
		
		logger.info("##### [ENDDING] : {}.{}() with returning : {}",
				signature.getDeclaringTypeName(),
				signature.getName(),
				result != null ? result.toString() : null);
	}
}
