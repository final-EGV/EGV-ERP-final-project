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

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingMethodTermination.class);
	
	/**
	 * A join point is in the business(service) layer, if the method is defined
	 * in a type in the org.erp.egv.theater.model.service package or any
	 * sub-packaged under that.
	 * <p>
	 * Target methods matched to this pointcut may have an @Service annotation
	 * defined.
	 */
	@Pointcut("within(org.erp.egv.theater.model.service..*)")
	public void inBusinessLayer() {}
	
	/**
	 * A join point is in the persistence(data access) layer, if the method is
	 * defined in a type in the org.erp.egv.theater.model.service package or any
	 * sub-packaged under that.
	 * <p>
	 * Target methods matched to this pointcut may have an @Repository
	 * annotation defined.
	 */
	@Pointcut("within(org.erp.egv.theater.model.dao..*)")
	public void inPersistenceLayer() {}
	
	@AfterReturning(pointcut = "inBusinessLayer() || inPersistenceLayer()",
			returning = "result")
	public void loggingMethodTermination(JoinPoint jp, Object result) {

		Signature signature = jp.getSignature();

		LOGGER.info("##### [TERMINATED] : {}.{}() with returning : {}",
				signature.getDeclaringTypeName(),
				signature.getName(),
				result != null ? result.toString() : null);
	}
}
