package com.fsoft.core.common.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.JSON;

/**
 * @package com.fsoft.core.common.base
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-06
 * @copyright 2009-2019 <BR>
 *            登录用户信息，实现SysUser信息复制
 */
@SuppressWarnings("serial")
public class UserVo implements Serializable {

	private String id;
	private String loginAcct;
	private String userName;
	private String currentOrgId;
	private String currentOrgName;
	private String currentAreaId;
	private String currentAreaName;
	/**
	 * theme 每个用户有自己的皮肤信息
	 */
	private String currTheme;

	private Set<String> permsSet = new HashSet<String>();
	private Set<String> roleSet = new HashSet<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginAcct() {
		return loginAcct;
	}

	public void setLoginAcct(String loginAcct) {
		this.loginAcct = loginAcct;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurrentOrgId() {
		return currentOrgId;
	}

	public void setCurrentOrgId(String currentOrgId) {
		this.currentOrgId = currentOrgId;
	}

	public String getCurrentOrgName() {
		return currentOrgName;
	}

	public void setCurrentOrgName(String currentOrgName) {
		this.currentOrgName = currentOrgName;
	}

	public Set<String> getPermsSet() {
		return permsSet;
	}

	public void setPermsSet(Set<String> permsSet) {
		this.permsSet = permsSet;
	}

	public String getCurrentAreaId() {
		return currentAreaId;
	}

	public void setCurrentAreaId(String currentAreaId) {
		this.currentAreaId = currentAreaId;
	}

	public String getCurrentAreaName() {
		return currentAreaName;
	}

	public void setCurrentAreaName(String currentAreaName) {
		this.currentAreaName = currentAreaName;
	}

	public Set<String> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<String> roleSet) {
		this.roleSet = roleSet;
	}

	public String getCurrTheme() {
		return currTheme;
	}

	public void setCurrTheme(String currTheme) {
		this.currTheme = currTheme;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
