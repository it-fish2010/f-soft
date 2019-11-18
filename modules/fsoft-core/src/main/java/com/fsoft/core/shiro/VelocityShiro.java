package com.fsoft.core.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.fsoft.core.utils.OgnlUtils;

/**
 * Shiro权限标签(Velocity版)
 * 
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-03-24
 * @copyright 佳乐软件股份有限公司© 2019-2019
 *
 */
public class VelocityShiro {

	/**
	 * 是否拥有该权限
	 * 
	 * @param permission 权限标识
	 * @return true：是 false：否
	 */
	public boolean hasPermission(String permission) {
		Subject subject = SecurityUtils.getSubject();
		if (OgnlUtils.isNotEmpty(subject) && subject.isPermitted(permission))
			return true;
		return false;
	}

}
