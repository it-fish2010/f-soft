package com.fsoft.core.aop;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 基础切面类
 * 
 * @package com.fsoft.core.aop
 * @author fish
 * @email it_Fish@aliyun.com
 * @create 2019-10-09
 * @copyright 2009-2019
 */
@Aspect
@Component
@Order(0)
public class BaseAspect {

	private static Logger log = LoggerFactory.getLogger(BaseAspect.class);
	private long beginTime;
	private long endTime;

	/***
	 * 横切关注点 - 处理全局的请求绑定 by WGJ 主要用于实时更新 BaseController 的各项属性
	 * 
	 * @author Fish 2019-03-23
	 * @param joinPoint
	 * @throws Exception
	 */
	@Before("execution(* com.fsoft..*.*Controller.*(..))")
	public void requestBind(JoinPoint joinPoint) throws Exception {
		beginTime = System.currentTimeMillis();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		response.addHeader("Access-Control-Allow-Origin", "*");
	}

	/**
	 * 横切关注点 - 统计所有 Controller 中的方法运行耗时 by WGJ
	 * @param joinPoint
	 */
	@After("execution(* com.fsoft..*.*Controller.*(..))")
	public void timeWatch(JoinPoint joinPoint) {
		endTime = System.currentTimeMillis();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		log.info(className + "." + methodName + "() 运行耗时：" + (endTime - beginTime) + " ms ");
	}
}
