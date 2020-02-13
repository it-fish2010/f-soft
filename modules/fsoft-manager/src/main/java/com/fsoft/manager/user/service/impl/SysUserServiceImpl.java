package com.fsoft.manager.user.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.core.Global;
import com.fsoft.core.common.QueryParam;
import com.fsoft.core.common.service.impl.BaseServiceImpl;
import com.fsoft.core.utils.DateTimeUtils;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.UUIDUtils;
import com.fsoft.manager.menu.entity.SysMenu;
import com.fsoft.manager.menu.service.SysMenuService;
import com.fsoft.manager.role.entity.SysRole;
import com.fsoft.manager.role.service.SysRoleService;
import com.fsoft.manager.user.entity.SysUser;
import com.fsoft.manager.user.mapper.SysUserMapper;
import com.fsoft.manager.user.service.SysUserService;

/**
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-03-24
 * @copyright 佳乐软件股份有限公司© 2019-2019
 */
@Service("sysUserService")
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysMenuService menuService;
	@Autowired
	private SysRoleService roleService;

	@Override
	public List<String> queryAllPerms(String userId) throws Exception {
		List<SysMenu> menus = menuService.findUserMenuList(userId);
		List<String> mList = new ArrayList<String>();
		for (SysMenu menu : menus) {
			mList.add(menu.getPerms());
		}
		return mList;
	}

	@Override
	public SysUser getEntity(String rwid) throws Exception {
		SysUser user = super.getEntity(rwid);
		if (OgnlUtils.isNotEmpty(user) && OgnlUtils.isEmpty(user.getRoleIdList())) {
			// 如果级联查询用户角色失败，通过角色Service查询一次
			List<SysRole> roleList = roleService.findRoleListByUserId(rwid);
			if (OgnlUtils.isNotEmpty(roleList)) {
				List<String> roles = new ArrayList<String>();
				for (SysRole sr : roleList) {
					roles.add(sr.getId());
				}
				user.setRoleIdList(roles);
			}
		}
		return user;
	}

	@Override
	public List<String> queryAllMenuId(String userId) throws Exception {
		return userMapper.selectUserMenuList(userId);
	}

	@Override
	public SysUser getSysUserByLoginAcct(String loginAcct) throws Exception {
		return userMapper.selectByLoginAcct(loginAcct);
	}

	@Override
	public int save(SysUser entity) throws Exception {
		if (StringUtils.isBlank(entity.getId()))
			entity.setId(UUIDUtils.randomUpperCaseId());
		entity.setLoginPwd(new Sha256Hash(entity.getLoginPwd()).toHex());
		if (OgnlUtils.isEmpty(entity.getCreateTime()))
			entity.setCreateTime(DateTimeUtils.getNowTime());
		if (OgnlUtils.isEmpty(entity.getStatus()))
			entity.setStatus(Global.STATUS_YES); // 默认启用
		if (OgnlUtils.isEmpty(entity.getIsLock()))
			entity.setIsLock(Global.STATUS_NO); // 默认未加锁

		if (OgnlUtils.isNotEmpty(entity.getRoleIdList())) {
			userMapper.insertBatchUserRole(entity);
		}
		return userMapper.insert(entity);
	}

	/****
	 * 根据用户ID标识，删除用户的角色关系
	 * @user fish(it.fish2010@foxmail.com)
	 * @date 2019-10-09
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	protected int removeUserRoleByUserId(String userid) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add(userid);
		return userMapper.deleteUserRoleBatch(list);
	}

	@Override
	public int modify(SysUser entity) throws Exception {
		// Controller层传入的密码为明文密码
		if (StringUtils.isNotBlank(entity.getLoginPwd()))
			entity.setLoginPwd(new Sha256Hash(entity.getLoginPwd()).toHex());
		// 角色标识不为空
		if (OgnlUtils.isNotEmpty(entity.getRoleIdList())) {
			userMapper.deleteUserRole(entity.getId());
			userMapper.insertBatchUserRole(entity);
		}
		return super.modify(entity);
	}

	@Override
	public int removeBatch(List<String> ids) throws Exception {
		userMapper.deleteUserRoleBatch(ids);
		return userMapper.deleteBatch(ids);
	}

	@Override
	public int updatePassword(String userId, String password, String newPassword) throws Exception {
		SysUser user = getEntity(userId);
		if (user == null)
			throw new Exception("用户信息已变更!");
		if (!StringUtils.equals(password, newPassword))
			throw new Exception("原密码错误,请重新输入旧密码!");
		user.setLoginPwd(newPassword);
		return userMapper.update(user);
	}

	@Override
	public void initPassword(String[] userIds) throws Exception {
		for (String userId : userIds) {
			SysUser user = getEntity(userId);
			user.setLoginPwd(new Sha256Hash("123456").toHex());
			// sysUserDao.update(user);
		}
	}

	@Override
	public List<SysUser> findUserByRoleId(String roleId) throws Exception {
		QueryParam param = new QueryParam();
		param.put("roleId", roleId);
		return findList(param);
	}

	@Override
	public int lockUser(List<SysUser> userList) throws Exception {
		int updInt = 0;
		for (SysUser user : userList) {
			if (OgnlUtils.isEmpty(user.getId()))
				continue;
			user.setIsLock(Global.STATUS_YES);
			if (OgnlUtils.isEmpty(user.getLockType()))
				user.setLockType(SysUser.LOCK_TYPE_ADMIN);
			user.setLockTime(DateTimeUtils.getNowTime());
			user.setModifyTime(DateTimeUtils.getNowTime());
			updInt += userMapper.updateUserLock(user);
		}
		return updInt;
	}

	@Override
	public int unlockUser(List<SysUser> userList) throws Exception {
		int updInt = 0;
		for (SysUser user : userList) {
			if (OgnlUtils.isEmpty(user.getId()))
				continue;
			user.setIsLock(Global.STATUS_NO);
			user.setLockType(null);
			user.setLockTime(null);
			user.setModifyTime(DateTimeUtils.getNowTime());
			updInt += userMapper.updateUserLock(user);
		}
		return updInt;
	}

	@Override
	public Map<String, BigDecimal> getIndexHomeUser(String orgId) throws Exception {
		return userMapper.selectSum(orgId);
	}

}
