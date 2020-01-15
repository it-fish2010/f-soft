package com.fsoft.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * F-Soft 系统日志注解，实现参考：com.fsoft.core.aop.SysLogAspect
 * @package com.fsoft.core.annotation
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-12-11
 * @CopyRight © F-Soft
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
	String value() default "";
}
