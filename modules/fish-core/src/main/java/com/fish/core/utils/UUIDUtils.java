package com.fish.core.utils;

import java.util.UUID;

/**
 * @package com.fish.core.utils
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-06
 * @copyright 2009-2019
 */
public class UUIDUtils {

	/**
	 * 返回一个大写的随机字符串
	 * @user Fish
	 * @date 2019-05-06
	 * @return
	 */
	public static final String randomUpperCaseId() {
		return randomLowerCaseId().toUpperCase();
	}

	/***
	 * 返回小写的随机ID
	 * @user Fish
	 * @date 2019-05-06
	 * @return
	 */
	public static final String randomLowerCaseId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
