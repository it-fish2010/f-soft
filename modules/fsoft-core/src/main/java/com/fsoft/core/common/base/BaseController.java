package com.fsoft.core.common.base;

import com.fsoft.core.shiro.ShiroContext;

/**
 * F-Soft 基础Controller
 * @package com.fsoft.core.common.base
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-02
 * @CopyRight © F-Soft
 **/
public abstract class BaseController {

	/***
	 * F-Soft 获取当前登录用户信息
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @return
	 */
	protected final UserVo getUser() {
		return ShiroContext.getCurrentUser();
	}

	/***
	 * F-Soft 当前登录用户的ID
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @return
	 */
	protected final String getUserId() {
		return getUser().getId();
	}

	/***
	 * F-Soft 当前登录用户的登录帐号
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @return
	 */
	protected final String getLoginAcct() {
		return getUser().getLoginAcct();
	}
}
