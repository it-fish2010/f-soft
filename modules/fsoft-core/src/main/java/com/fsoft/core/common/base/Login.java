package com.fsoft.core.common.base;

/*********
 * F-Soft 登录的实体Bean
 * @package com.fsoft.core.common.base
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-12-11
 * @CopyRight © F-Soft
 **********/
public class Login {
	private String username;
	private String password;
	private String captcha;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
