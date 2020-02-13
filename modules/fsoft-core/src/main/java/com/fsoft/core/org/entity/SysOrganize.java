package com.fsoft.core.org.entity;

import com.fsoft.core.common.base.BaseTreeVo;

/**
 * F-Soft 组织机构实体Bean
 * 
 * @package com.fsoft.core.org.entity
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-16
 * @CopyRight © F-Soft
 **/
@SuppressWarnings("serial")
public class SysOrganize extends BaseTreeVo {
	private String parentCode;
	private String parentName;

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

}
