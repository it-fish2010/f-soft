package com.fsoft.core.dict.entity;

import org.springframework.stereotype.Repository;

import com.fsoft.core.common.base.BaseVo;

/**
 * 字典表
 * 
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-03-25 08:13:58
 * @copyright 哲凌科技有限公司 2019-${year}
 **/
@SuppressWarnings("serial")
@Repository
public class SysDict extends BaseVo {
	// 字典类型，1：列表，2：树形
	private Integer dictType;
	private Integer status;

	public Integer getDictType() {
		return dictType;
	}

	public void setDictType(Integer dictType) {
		this.dictType = dictType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
