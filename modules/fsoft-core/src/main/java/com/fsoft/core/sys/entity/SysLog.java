package com.fsoft.core.sys.entity;

import com.fsoft.core.common.base.BaseVo;

/**
 * 系统日志
 * 
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-03-27
 * @copyright 佳乐软件股份有限公司© 2019-2019
 *
 */
@SuppressWarnings("serial")
public class SysLog extends BaseVo {
	private String operateName;
	private String operation;
	// 请求方法
	private String method;
	// 请求参数
	private String params;

	private String remark;
	// IP地址
	private String ip;

	private String createUserAcct;
	private String createUserName;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getCreateUserAcct() {
		return createUserAcct;
	}

	public void setCreateUserAcct(String createUserAcct) {
		this.createUserAcct = createUserAcct;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

}
