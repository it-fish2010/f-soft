package com.fsoft.core.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestBinder {

	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();

	public static HttpServletRequest getRequest() {
		return requestLocal.get();
	}

	public static HttpSession getSession() {
		return requestLocal.get().getSession();
	}

	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}

	public static void removeAttribute() {
		requestLocal.remove();
	}

}