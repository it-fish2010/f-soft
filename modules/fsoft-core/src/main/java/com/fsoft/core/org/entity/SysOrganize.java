package com.fsoft.core.org.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.alibaba.fastjson.JSON;

/**
 * F-Soft 组织机构实体Bean
 * @package com.fsoft.core.org.entity
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-16
 * @CopyRight © F-Soft
 **/
public class SysOrganize implements Serializable {
	private static final long serialVersionUID = 1L;

	private String rwid;
	private String orgCode;
	private String orgName;
	private String parentOrgId;
	private String parentOrgCode;
	private String parentOrgName;
	//单位树ID 的拼接串，可通过like 语法查询所有下级
	private String parentOrgs;
	private String createUserId;
	private Timestamp createTime;
	private String modifyUserId;
	private Timestamp modifyTime;

	public String getRwid() {
		return rwid;
	}

	public void setRwid(String rwid) {
		this.rwid = rwid;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getParentOrgCode() {
		return parentOrgCode;
	}

	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public String getParentOrgs() {
		return parentOrgs;
	}

	public void setParentOrgs(String parentOrgs) {
		this.parentOrgs = parentOrgs;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
