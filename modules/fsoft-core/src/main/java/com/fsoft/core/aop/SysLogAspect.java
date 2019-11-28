package com.fsoft.core.aop;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.fsoft.core.annotation.SystemLog;
import com.fsoft.core.common.base.UserVo;
import com.fsoft.core.shiro.ShiroContext;
import com.fsoft.core.sys.entity.SysLog;
import com.fsoft.core.sys.service.SysLogService;
import com.fsoft.core.utils.DateTimeUtils;
import com.fsoft.core.utils.HttpContextUtils;
import com.fsoft.core.utils.IPUtils;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.UUIDUtils;

/**
 * F-Soft 系统日志切面 在需要记录日志的Controller，使用@SysLog 标签，可实现日志记录
 * @package com.fsoft.core.aop
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-25
 * @CopyRight © F-Soft
 **/
@Aspect
@Component
@Order(1)
public class SysLogAspect {
	@Autowired
	private SysLogService sysLogService;
	private Logger log = LoggerFactory.getLogger(SysLogAspect.class);
	private long startTime = 0;

	@Pointcut("@annotation(com.fsoft.core.annotation.SystemLog)")
	public void logPointCut() {

	}

	@Before("logPointCut()")
	public void startSystemLogAspect(JoinPoint joinPoint) {
		this.startTime = System.currentTimeMillis();
	}

	@After("logPointCut()")
	public void saveSysLog(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		// 系统日志的Bean
		SysLog entity = new SysLog();
		SystemLog systemLog = signature.getMethod().getAnnotation(SystemLog.class);
		// 操作
		entity.setOperateName(systemLog.value());
		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		entity.setMethod(className + "." + methodName + "()");
		// 请求的参数
		Object[] args = joinPoint.getArgs();
		StringBuffer params = new StringBuffer();
		for (Object arg : args) {
			if (OgnlUtils.isNotEmpty(arg))
				params.append(JSON.toJSONString(arg));
		}
		entity.setParams(params.toString());
		// 获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		// 设置IP地址
		entity.setIp(IPUtils.getIpAddr(request));
		try {
			entity.setCreateTime(DateTimeUtils.getNowTime());
			if (ShiroContext.getCurrentUser() != null) {
				UserVo user = ShiroContext.getCurrentUser();
				entity.setCreateUserId(user.getId()); // 操作人ID
				StringBuffer operation = new StringBuffer();
				operation.append("用户[" + user.getUserName() + "]使用账号[" + user.getLoginAcct() + "]");
				operation.append("执行操作(" + systemLog.value() + "),");
				operation.append("操作时间:").append(DateTimeUtils.formatDate(entity.getCreateTime()));
				operation.append(",客户端IP：").append(StringUtils.isBlank(entity.getIp()) ? "未知IP" : entity.getIp());
				entity.setOperation(operation.toString());
			}
			if (OgnlUtils.isEmpty(entity.getId()))
				entity.setId(UUIDUtils.randomUpperCaseId());
			// 保存系统日志
			long times = System.currentTimeMillis() - startTime;
			if (startTime > 0)
				entity.setRemark("耗时:" + times + "ms");
			sysLogService.save(entity);
		} catch (Exception e) {
			log.error("记录日志发生异常", e);
		}
	}

}
