package com.fish.core.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Http上下文工具类
 * @package com.fish.core.utils
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-05
 * @copyright 2009-2019
 */
public class HttpContextUtils {

	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
}
