package com.fish.core.common.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.fish.core.common.exceptions.RRException;

/**
 * hibernate-validator校验工具类
 *
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 * @package com.fish.core.common.validator
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-05
 * @copyright 2009-2019
 */
public class ValidatorUtils {
	private static Validator validator;

	static {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * 校验对象
	 * @param object 待校验对象
	 * @param groups 待校验的组
	 * @throws RRException 校验不通过，则报RRException异常
	 */
	public static void validateEntity(Object object, Class<?>... groups) throws RRException {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			ConstraintViolation<Object> constraint = (ConstraintViolation<Object>) constraintViolations.iterator().next();
			throw new RRException(constraint.getMessage());
		}
	}
}
