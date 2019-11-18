package com.fsoft.manager.shiro;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fsoft.core.common.base.UserVo;
import com.fsoft.core.shiro.ShiroContext;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;

/**
 * 利用Freemarker标签的方式，处理前端页面的权限配置问题
 * @package com.fsoft.manager.shiro
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-08
 * @copyright 2009-2019
 * 
 */
@SuppressWarnings("rawtypes")
public class SysPermissionModel implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if (body != null) {
			TemplateScalarModel valueModel = (TemplateScalarModel) params.get("value");
			String perms = valueModel.getAsString();
			if (StringUtils.isBlank(perms) || getRightRes(perms)) {
				body.render(env.getOut());
			}
		} else {
			throw new TemplateException("标签内无内容", env);
		}

	}

	/***
	 * 当前用户有权限
	 * @user Fish
	 * @date 2019-05-08
	 * @param perms
	 * @return
	 */
	private boolean getRightRes(String perms) {
		UserVo user = ShiroContext.getCurrentUser();
		return user.getPermsSet().contains(perms);
	}

}
