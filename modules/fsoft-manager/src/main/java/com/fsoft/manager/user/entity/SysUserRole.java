package com.fsoft.manager.user.entity;

import com.fsoft.core.common.base.BaseVo;

/**
 * @package com.fsoft.manager.user.entity
 * @author fish
 * @email it.fish2010@foxmail.com
 * @create 2019-10-09
 * @copyright 2009-2019
 */
public class SysUserRole extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
