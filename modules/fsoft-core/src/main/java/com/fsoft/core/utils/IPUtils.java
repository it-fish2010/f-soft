package com.fsoft.core.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;

/**
 * F-Soft IP工具类，主要功能：通过HttpServletRequest 获取客户端的IP地址
 * @package com.fsoft.core.utils
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-25
 * @CopyRight © F-Soft
 **/
public class IPUtils {
	private static Logger logger = LoggerFactory.getLogger(IPUtils.class);

	/**
	 * 通过http Header 获取IP地址 <BR>
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址 <BR>
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址;X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static final String getIpAddr(HttpServletRequest request) {
		String ip = null;
		try {
			ip = request.getHeader("x-forwarded-for");
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("fsoft-web-clientIp"); // 优先通过项目自身的JS插件获取客户端IP
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} catch (Exception e) {
			logger.error("IPUtils ERROR ", e);
		}
		return ip;
	}

}
