package com.fish.core.xss;

import org.apache.commons.lang.StringUtils;

import com.fish.core.common.exceptions.RRException;

/**
 * @package com.fish.core.xss
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-05
 * @copyright 2009-2019
 */
public class SQLFilter {

	/**
	 * SQL注入过滤
	 * @param str 待验证的字符串
	 */
	public static String sqlInject(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		// 去掉'|"|;|\字符
		str = StringUtils.replace(str, "'", "");
		str = StringUtils.replace(str, "\"", "");
		str = StringUtils.replace(str, ";", "");
		str = StringUtils.replace(str, "\\", "");

		// 转换成小写
		str = str.toLowerCase();

		// 非法字符
		String[] keywords = { "master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop" };
		// 判断是否包含非法字符
		for (String keyword : keywords) {
			if (str.indexOf(keyword) != -1) {
				throw new RRException("包含非法字符");
			}
		}

		return str;
	}
}
