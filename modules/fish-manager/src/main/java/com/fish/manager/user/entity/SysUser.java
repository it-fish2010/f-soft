package com.fish.manager.user.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fish.core.common.base.BaseVo;

@SuppressWarnings("serial")
public class SysUser extends BaseVo {
	/***
	 * 0-管理员手工加锁
	 */
	public static final Integer LOCK_TYPE_ADMIN = new Integer("0");
	/**
	 * 1-登录次数超限制
	 */
	public static final Integer LOCK_TYPE_LOGINTIMES = new Integer("1");
	/***
	 * 2-密码校验次数超限制
	 */
	public static final Integer LOCK_TYPE_PASSWORDTIMES = new Integer("2");
	/**
	 * 3-密码过期
	 */
	public static final Integer LOCK_TYPE_PASSWORDOVERTIME = new Integer("3");
	/***
	 * 4-帐号长期未使用
	 */
	public static final Integer LOCK_TYPE_NEVERLOGIN = new Integer("4");

	private String loginAcct;
	private String loginPwd;
	private String userName;

	private String email;
	private String mobile;
	private Integer status;

	private Integer isLock;
	private Integer lockType;
	private Timestamp lockTime;

	private String areaId;
	private String areaName;

	private String orgName;
	// 默认皮肤;保存在用户信息中
	private String theme;
	private List<String> roleIdList = new ArrayList<String>();

	public String getLoginAcct() {
		return loginAcct;
	}

	public void setLoginAcct(String loginAcct) {
		this.loginAcct = loginAcct;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public Integer getLockType() {
		return lockType;
	}

	public void setLockType(Integer lockType) {
		this.lockType = lockType;
	}

	public Timestamp getLockTime() {
		return lockTime;
	}

	public void setLockTime(Timestamp lockTime) {
		this.lockTime = lockTime;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}
}