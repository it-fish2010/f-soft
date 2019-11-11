package com.fish.manager.user.service;

import java.util.List;

import com.fish.core.common.service.BaseService;
import com.fish.manager.user.entity.SysUser;

public interface SysUserService extends BaseService<SysUser> {

	/**
	 * 查询用户的所有权限
	 * 
	 * @param userId 用户ID
	 */
	List<String> queryAllPerms(String userId) throws Exception;

	/**
	 * 查询用户的所有菜单ID
	 */
	List<String> queryAllMenuId(String userId) throws Exception;

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUser getSysUserByLoginAcct(String loginAcct) throws Exception;

	/**
	 * 修改密码
	 * 
	 * @param userId 用户ID
	 * @param password 原密码
	 * @param newPassword 新密码
	 */
	int updatePassword(String userId, String password, String newPassword) throws Exception;

	void initPassword(String[] userIds) throws Exception;

	/***
	 * 根据角色ID，查询用户信息
	 * @user Fish
	 * @date 2019-05-08
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<SysUser> findUserByRoleId(String roleId) throws Exception;

	/***
	 * 用户帐号批量加锁
	 * @user fish(it.fish2010@foxmail.com)
	 * @date 2019-10-09
	 * @param userList
	 * @return
	 * @throws Exception
	 */
	int lockUser(List<SysUser> userList) throws Exception;

	/***
	 * 用户帐号批量解锁
	 * @user fish(it.fish2010@foxmail.com)
	 * @date 2019-10-09
	 * @param userList
	 * @return
	 * @throws Exception
	 */
	int unlockUser(List<SysUser> userList) throws Exception;

}
