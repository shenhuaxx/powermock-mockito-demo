//package com.shenhua.powermock.mockito.demo.aspect;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Aspect
//@Configuration
//public class ServiceAspect {
//	/**
//	 * Logger for this class
//	 */
//	private static final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);
//	
//	@Around("execution(* com.shenhua.powermock.mockito.demo.service.impl.*.*(..))")
//	public Object serviceAround(ProceedingJoinPoint joinPoint) throws Throwable {
//		
//		String className = joinPoint.getSignature().getDeclaringTypeName();
//		String methodName = joinPoint.getSignature().getName();
//		String serviceName = String.format("%s.%s", className, methodName);
//		Object[] params = joinPoint.getArgs();
//		logger.info("[{}] param: {}", serviceName, new ObjectMapper().writeValueAsString(params));
//		Object result = joinPoint.proceed(params);
//		logger.info("[{}] result: {}", serviceName, new ObjectMapper().writeValueAsString(result));
//		return result;
//	}
//
//}
