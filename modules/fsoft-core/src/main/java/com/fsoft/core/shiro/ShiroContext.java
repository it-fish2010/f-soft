package com.fsoft.core.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.fsoft.core.common.base.UserVo;

public class ShiroContext {

	public static final Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static final Session getSession() {
		return getSubject().getSession();
	}

	public static final UserVo getCurrentUser() {
		return (UserVo) getSubject().getPrincipal();
	}

	public static String getCurrUserId() {
		return getCurrentUser().getId();
	}

	public static String getCurrLoginAcct() {
		return getCurrentUser().getLoginAcct();
	}

	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		getSubject().logout();
	}

	public static String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}

}
