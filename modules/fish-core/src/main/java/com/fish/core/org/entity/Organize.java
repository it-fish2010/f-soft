package com.fish.core.org.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class Organize implements Serializable {
	private static final long serialVersionUID = 1L;

	// 部门标识
	private String orgId;
	// 部门编号
	private String orgCode;
	// 部门名称
	private String orgName;
	// 上一级部门标识
	private String parentOrgId;
	// 排序
	private BigDecimal sortNo;
	// 是否显示 0：否 1：是
	private String state;
	// 部门等级串
	private String levelOrg;
	// 所属地区
	private String theArea;
	// 备注
	private String note;
	// 添加时间
	private Date inDate;
	private boolean open;

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	// 父部门名称
	private String parentOrgName;
	// 父部门编号
	private String parentOrgCode;

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

	/**
	 * 设置：部门标识
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取：部门标识
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置：部门编号
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * 获取：部门编号
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * 设置：部门名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取：部门名称
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置：上一级部门标识
	 */
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	/**
	 * 获取：上一级部门标识
	 */
	public String getParentOrgId() {
		return parentOrgId;
	}

	/**
	 * 设置：排序
	 */
	public void setSortNo(BigDecimal sortNo) {
		this.sortNo = sortNo;
	}

	/**
	 * 获取：排序
	 */
	public BigDecimal getSortNo() {
		return sortNo;
	}

	/**
	 * 设置：是否显示 0：否 1：是
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取：是否显示 0：否 1：是
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置：部门等级串
	 */
	public void setLevelOrg(String levelOrg) {
		this.levelOrg = levelOrg;
	}

	/**
	 * 获取：部门等级串
	 */
	public String getLevelOrg() {
		return levelOrg;
	}

	/**
	 * 设置：所属地区
	 */
	public void setTheArea(String theArea) {
		this.theArea = theArea;
	}

	/**
	 * 获取：所属地区
	 */
	public String getTheArea() {
		return theArea;
	}

	/**
	 * 设置：备注
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 获取：备注
	 */
	public String getNote() {
		return note;
	}

	/**
	 * 设置：添加时间
	 */
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	/**
	 * 获取：添加时间
	 */
	public Date getInDate() {
		return inDate;
	}
}
