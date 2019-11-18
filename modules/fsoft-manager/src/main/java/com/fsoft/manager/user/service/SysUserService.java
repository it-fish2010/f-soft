package com.fsoft.manager.user.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fsoft.core.common.service.BaseService;
import com.fsoft.manager.user.entity.SysUser;

public interface SysUserService extends BaseService<SysUser> {

	/**
	 * 查询用户的所有权限
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

	/***
	 * F-Soft 提供给首页调用-统计当前单位的用户数据
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-16
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	Map<String, BigDecimal> getIndexHomeUser(String orgId) throws Exception;

}
