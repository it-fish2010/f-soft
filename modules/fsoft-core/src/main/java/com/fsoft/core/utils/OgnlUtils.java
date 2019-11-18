package com.fsoft.core.utils;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @package com.zheling.fangw.common.util
 * @email it.fish2010@foxmail.com
 * @author Fish
 * @create 2018-12-22
 * @copyRight 2017-2018 广西哲凌科技有限公司
 * @desc
 */
@SuppressWarnings(value = { "rawtypes" })
public class OgnlUtils {
	/**
	 * 可以用于判断 Map,Collection,String,Array,Long是否为空
	 * 
	 * @param o java.lang.Object.
	 * @return boolean.
	 */
	public static boolean isEmpty(Object o) {
		if (o == null)
			return true;
		if (o instanceof String && StringUtils.isBlank((String) o)) {
			return true;
		} else if (o instanceof Collection && ((Collection) o).isEmpty()) {
			return true;
		} else if (o.getClass().isArray() && ((Object[]) o).length == 0) {
			return true;
		} else if (o instanceof Map && ((Map) o).isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 可以用于判断 Map,Collection,String,Array是否不为空
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	/**
	 * 首字母转小写
	 * 
	 * @author Fish 2019年3月22日
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstChar(String s) {
		if (StringUtils.isBlank(s))
			return null;
		return s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toLowerCase());
	}

	/***
	 * 首字母转大写
	 * 
	 * @USER Fish 2019年3月22日
	 * @param s
	 * @return
	 */
	public static String toUpCaseFirstChar(String s) {
		if (StringUtils.isBlank(s))
			return null;
		return s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toUpperCase());
	}
}
