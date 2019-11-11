package com.fish.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统日志注解，实现参考：com.fish.core.aop.SysLogAspect
 * @package com.fish.core.annotation
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-08
 * @copyright 2009-2019
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
	String value() default "";
}
