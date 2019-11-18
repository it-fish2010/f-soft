package com.fsoft.core.common.validator;

import org.apache.commons.lang.StringUtils;

import com.fsoft.core.common.exceptions.RRException;

/**
 * @package com.fsoft.core.common.validator
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-05
 * @copyright 2009-2019
 */
public abstract class Assert {

	public static void isBlank(String str, String message) {
		if (StringUtils.isBlank(str)) {
			throw new RRException(message);
		}
	}

	public static void isNull(Object object, String message) {
		if (object == null) {
			throw new RRException(message);
		}
	}
}
