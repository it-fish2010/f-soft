package com.fsoft.core.sys.entity;

import org.hibernate.validator.constraints.NotBlank;

import com.fsoft.core.common.base.BaseVo;

/**
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-03-23
 * @copyright 佳乐软件股份有限公司© 2019-2019
 *
 */
@SuppressWarnings("serial")
public class SysConfig extends BaseVo {
	private String id;
	@NotBlank(message = "参数名不能为空")
	private String code;
	@NotBlank(message = "参数值不能为空")
	private String value;
	private String remark;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
