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
import org.aspectj.lang.annotation.Pointcut;
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
	
	/**
	 * A join point is in the presentation(web) layer, if the method is defined
	 * in a type in the org.erp.egv.theater.controller package or any
	 * sub-packaged under that.
	 * <p>
	 * Target methods matched to this pointcut may have an @Controller
	 * annotation defined.
	 */
	@Pointcut("within(org.erp.egv.theater.controller..*)")
	public void inPresentationLayer() {}
	
	@Around("inPresentationLayer()")
	public Object loggingAllRequest(ProceedingJoinPoint pjp) throws Throwable {
		
		Signature signature = pjp.getSignature();
		
		Class<? extends Object> targetObjectType = pjp.getTarget().getClass();
		
		String requestedParams = getRequestParam();
		String requestedUrl = getRequestUrl(pjp, targetObjectType);
		String requestedIpAddr = getRequestIpAddress();
		
		LOGGER.info("##### [REQUEST] --> From IP Address : \'" + requestedIpAddr
				+ "\', with URL : \'" + requestedUrl + "\'");
		LOGGER.info("##### [REQUEST] --> {}.{}() with parameters [{}]",
				signature.getDeclaringTypeName(),
				signature.getName(),
				requestedParams);
		
		StopWatch stopWatch = new StopWatch();
		
		stopWatch.start();	// timer start
		
		Object response = pjp.proceed();
		
		stopWatch.stop();	// timer stop
		
		LOGGER.info("##### [RESPONSE] <-- {}.{}() with response [{}], elapsed time : {} msec.",
				signature.getDeclaringTypeName(),
				signature.getName(),
				response,
				stopWatch.getTotalTimeMillis());
		
		return response;
	}
	
	/**
	 * @return IP Address of request from client
	 */
	private String getRequestIpAddress() {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		
		return ipAddress;
	}
	
//	private String getRequestIpAddress() {
//		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRemoteAddr();
//	}

	/**
	 * Get request URL from current join point and target object.
	 * 
	 * @param pjp ProceedingJoinPoint of @Around advice
	 * @param targetObjectType Type of target object
	 * @return URL of request from client
	 * @see <a href="https://gaemi606.tistory.com/entry/Spring-Boot-AOP%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%B4-%EB%A1%9C%EA%B7%B8-%EC%B6%9C%EB%A0%A5%ED%95%98%EA%B8%B0-REST-API">reference materials</a>
	 * @see #getUrl()
	 */
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
	
	/**
	 * Return the combination of HTTP method and request URL.
	 * 
	 * @param method target method of target object
	 * @param annotationClass annotation declared in the target method
	 * @param baseUrl the value of @RequestMapping annotation defined in target object
	 * @return the combination of HTTP method and request URL
	 * @see <a href="https://gaemi606.tistory.com/entry/Spring-Boot-AOP%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%B4-%EB%A1%9C%EA%B7%B8-%EC%B6%9C%EB%A0%A5%ED%95%98%EA%B8%B0-REST-API">reference materials</a>
	 * @see #getRequestUrl()
	 */
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

	/**
	 * Returns a java.util.Map of the parameters of this request.
	 * 
	 * @return request
	 * @see <a href="https://shinsunyoung.tistory.com/67">reference materials</a>
	 * @see #paramMapToString()
	 */
	private String getRequestParam() {
		
		String params = "EMPTY";
		
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			
			Map<String, String[]> paramMap = request.getParameterMap();
			
			if (!paramMap.isEmpty()) {
				params = paramMapToString(paramMap);
			}
		}
		
		return params;
	}

	/**
	 * @param paramMap java.util.Map to convert
	 * @return a string representation of the given Map
	 * @see <a href="https://shinsunyoung.tistory.com/67">reference materials</a>
	 * @see #getRequestParam()
	 */
	private String paramMapToString(Map<String, String[]> paramMap) {
		return paramMap.entrySet().stream()
				.map(entry -> String.format("%s -> \"%s\"",
						entry.getKey(), Joiner.on(",").join(entry.getValue())))
				.collect(Collectors.joining(", "));
	}

}
