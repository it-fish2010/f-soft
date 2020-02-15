package com.fsoft.manager.area.entity;

import com.fsoft.core.common.base.BaseTreeVo;

/**
 * F-Soft
 * @package com.fsoft.manager.area.entity
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2020-02-15
 * @CopyRight © F-Soft
 **/
@SuppressWarnings("serial")
public class SysArea extends BaseTreeVo {
	private Integer isCity;
	private String parentCode;
	private String parentName;

	private String areaCode;
	private String postCode;

	public Integer getIsCity() {
		return isCity;
	}

	public void setIsCity(Integer isCity) {
		this.isCity = isCity;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}
