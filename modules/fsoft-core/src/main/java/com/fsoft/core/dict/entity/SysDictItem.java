package com.fsoft.core.dict.entity;

import org.springframework.stereotype.Repository;

import com.fsoft.core.common.base.BaseTreeVo;

/**
 * 字典配置项明细表
 * 
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-03-25 08:13:58
 * @copyright 哲凌科技有限公司 2019-${year}
 **/
@SuppressWarnings("serial")
@Repository
public class SysDictItem extends BaseTreeVo {

	private String dictId;
	private String dictCode;
	private String dictName;
	private Integer status;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
