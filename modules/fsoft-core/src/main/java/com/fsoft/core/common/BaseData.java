package com.fsoft.core.common;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * F-Soft 基础数据的实体Bean
 * @package com.fsoft.core.common
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-02
 * @CopyRight © F-Soft
 **/
public class BaseData implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private String value;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
