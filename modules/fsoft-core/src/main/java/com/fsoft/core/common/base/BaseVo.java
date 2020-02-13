package com.fsoft.core.common.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.alibaba.fastjson.JSON;

/**
 * F-Soft 基础的数据Bean
 * 
 * @package com.fsoft.core.common.base
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-02
 * @CopyRight © F-Soft
 **/
@SuppressWarnings("serial")
public class BaseVo implements Serializable {
	private String id;
	private String code;
	private String name;
	/**
	 * 排序
	 */
	private BigDecimal sortNo;
	/**
	 * 状态
	 */
	private Integer status;

	private String orgId;
	private String createUserId;
	private Timestamp createTime;
	private String modifyUserId;
	private Timestamp modifyTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSortNo() {
		return sortNo;
	}

	public void setSortNo(BigDecimal sortNo) {
		this.sortNo = sortNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
