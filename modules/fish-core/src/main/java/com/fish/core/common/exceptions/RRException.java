package com.fish.core.common.exceptions;

/**
 * 自定义异常
 * 
 * @package com.fish.core.common.exceptions
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019年3月22日
 * @copyright ©佳松柏软件 2019-2019
 *
 */
public class RRException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String msg;
	private int code = 500;

	public RRException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public RRException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public RRException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public RRException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
