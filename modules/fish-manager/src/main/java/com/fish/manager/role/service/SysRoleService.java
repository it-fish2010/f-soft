package com.fish.manager.role.service;

import java.util.List;

import com.fish.core.common.service.BaseService;
import com.fish.manager.role.entity.SysRole;

/**
 * @package com.fish.manager.role.service
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-08
 * @copyright 2009-2019
 */
public interface SysRoleService extends BaseService<SysRole> {

	List<SysRole> findRoleListByUserId(String userid) throws Exception;

	/***
	 * 角色编码查询角色
	 * @user Fish
	 * @date 2019-05-08
	 * @param roleCode
	 * @return
	 * @throws Exception
	 */
	SysRole getRoleByCode(String roleCode) throws Exception;
}
