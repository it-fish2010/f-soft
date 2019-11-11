package com.fish.core.utils;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;

/**
 * @package com.fish.core.utils
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-06
 * @copyright 2009-2019
 */
public class RetVo extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	private int code;
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		this.put("code", code);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
		this.put("msg", msg);
	}

	public static final RetVo error() {
		return error(500, "未知异常，请联系管理员");
	}

	public static final RetVo error(String msg) {
		return error(500, msg);
	}

	public static final RetVo error(int code, String msg) {
		RetVo r = new RetVo();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static final RetVo ok(String msg) {
		RetVo vo = new RetVo();
		vo.put("code", "0");
		vo.put("msg", msg);
		return vo;
	}
	/***
	 * 数据查询，返回总数和数据列表 （layui支持count、data默认参数，自动分页）
	 * @user Fish 
	 * @date 2019-05-07
	 * @param count
	 * @param data
	 * @return
	 */
	public static final RetVo ok(long count, Object data) {
		RetVo vo = RetVo.ok();
		vo.put("count", count);
		vo.put("data", data);
		return vo;
	}

	public static final RetVo ok() {
		return ok("");
	}

	public RetVo put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
