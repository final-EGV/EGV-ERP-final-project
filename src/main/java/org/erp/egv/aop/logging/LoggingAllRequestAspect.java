package org.erp.egv.aop.logging;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.base.Joiner;

@Component
@Aspect
public class LoggingAllRequestAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAllRequestAspect.class);
	
	@Around("execution(* org.erp.egv.theater..*Controller.*(..))")
	public Object loggingAllRequest(ProceedingJoinPoint pjp) throws Throwable {
		
		Signature signature = pjp.getSignature();
		
		Class<? extends Object> targetObjectType = pjp.getTarget().getClass();
		
		String requestedParams = getRequestParams();
		String requestedUrl = getRequestUrl(pjp, targetObjectType);
		String requestedIpAddr = getRequestIpAddress();
		
		LOGGER.info("##### [REQUEST] : IP Addr : \'" + requestedIpAddr
				+ "\', URL : \'" + requestedUrl + "\'");
		LOGGER.info("##### [REQUEST] : {}.{}() with parameters {}",
				signature.getDeclaringTypeName(),
				signature.getName(),
				requestedParams);
		
		StopWatch stopWatch = new StopWatch();
		
		stopWatch.start();	// timer start
		
		Object response = pjp.proceed();
		
		stopWatch.stop();	// timer stop
		
		LOGGER.info("##### [RESPONSE] : {}.{}() = {}, elapsed {} msec",
				signature.getDeclaringTypeName(),
				signature.getName(),
				response,
				stopWatch.getTotalTimeMillis());
		
		return response;
	}

	private String getRequestIpAddress() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRemoteAddr();
	}

	private String getRequestUrl(ProceedingJoinPoint pjp, Class<? extends Object> targetObjectType) {
		
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method method = methodSignature.getMethod();
		RequestMapping requestMapping = (RequestMapping) targetObjectType.getAnnotation(RequestMapping.class);
		String baseUrl = requestMapping.value()[0];
		
		return Stream.of( GetMapping.class, PutMapping.class, PostMapping.class, PatchMapping.class, DeleteMapping.class, RequestMapping.class)
						.filter(mappingClass -> method.isAnnotationPresent(mappingClass))
						.map(mappingClass -> getUrl(method, mappingClass, baseUrl))
						.findFirst().orElse(null);
	}
	
	private String getUrl(Method method, Class<? extends Annotation> annotationClass, String baseUrl){
		
		Annotation annotation = method.getAnnotation(annotationClass);
		String[] value;
		String httpMethod = null;
		
		try {
			value = (String[])annotationClass.getMethod("value").invoke(annotation);
			httpMethod = (annotationClass.getSimpleName().replace("Mapping", "")).toUpperCase();
		} catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			return null;
		}
		
		return String.format("%s %s%s", httpMethod, baseUrl, value.length > 0 ? value[0] : "") ;
	}

	private String getRequestParams() {
		
		String params = "EMPTY";
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			
			Map<String, String[]> paramMap = request.getParameterMap();
			
			if (!paramMap.isEmpty()) {
				params = " [" + paramMapToString(paramMap) + "]";
			}
		}
		
		return params;
	}

	private String paramMapToString(Map<String, String[]> paramMap) {
		return paramMap.entrySet().stream()
				.map(entry -> String.format("%s -> \"%s\"",
						entry.getKey(), Joiner.on(",").join(entry.getValue())))
				.collect(Collectors.joining(", "));
	}

}
