package com.fsoft.manager.shiro;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.fsoft.core.Global;
import com.fsoft.core.common.base.UserVo;
import com.fsoft.core.shiro.ShiroContext;
import com.fsoft.core.utils.DateTimeUtils;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.manager.menu.service.SysMenuService;
import com.fsoft.manager.role.entity.SysRole;
import com.fsoft.manager.role.service.SysRoleService;
import com.fsoft.manager.user.entity.SysUser;
import com.fsoft.manager.user.service.SysUserService;

public class UserRealm extends AuthorizingRealm {
	public static final String CURRENT_USER = "currentUser";
	@Autowired
	private SysMenuService menuService;
	@Autowired
	private SysUserService userService;
	@Autowired
	private SysRoleService roleService;

	/**
	 * 授权(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserVo user = (UserVo) principals.getPrimaryPrincipal();
		try {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			info.setStringPermissions(user.getPermsSet());
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String loginAcct = (String) token.getPrincipal();
		String loginPwd = new String((char[]) token.getCredentials());
		try {
			SysUser sysUser = userService.getSysUserByLoginAcct(loginAcct);
			// 账号不存在
			if (OgnlUtils.isEmpty(sysUser))
				throw new UnknownAccountException("账号或密码不正确");
			// 密码错误
			if (!loginPwd.equals(sysUser.getLoginPwd())) {
				throw new IncorrectCredentialsException("账号或密码不正确");
			}
			// 账号已停用/禁用
			if (OgnlUtils.isEmpty(sysUser.getStatus()) || sysUser.getStatus().compareTo(Global.STATE_NO) == 0) {
				throw new AccountException("账号已停用/禁用,请联系管理员");
			}
			if (OgnlUtils.isNotEmpty(sysUser.getIsLock()) && Global.STATE_YES.compareTo(sysUser.getIsLock()) == 0) {
				throw new LockedAccountException("账号已锁定，锁定时间[" + DateTimeUtils.formatDateTime(sysUser.getLockTime()) + "],请联系管理员");
			}

			UserVo currUser = new UserVo();
			currUser.setId(sysUser.getId());
			currUser.setLoginAcct(sysUser.getLoginAcct());
			currUser.setUserName(sysUser.getUserName());
			currUser.setCurrentOrgId(sysUser.getOrgId());
			currUser.setCurrentOrgName(sysUser.getOrgName());
			currUser.setCurrentAreaId(sysUser.getAreaId());
			currUser.setCurrentAreaName(sysUser.getAreaName());
			// 默认皮肤
			currUser.setCurrTheme(OgnlUtils.isEmpty(sysUser.getTheme()) ? "theme1" : sysUser.getTheme());
			// 设置当前用户的权限信息
			List<String> userPerms = menuService.findUserPermList(currUser.getId());
			if (OgnlUtils.isEmpty(userPerms)) {
				throw new AuthenticationException("用户未分配任何权限，请联系管理员!");
			}
			Set<String> permsSet = new HashSet<String>();
			for (String perms : userPerms) {
				if (StringUtils.isBlank(perms))
					continue;
				permsSet.addAll(Arrays.asList(perms.trim().split(",")));
			}
			currUser.setPermsSet(permsSet);
			// 设置当前用户的角色信息
			List<SysRole> roles = roleService.findRoleListByUserId(sysUser.getId());
			Set<String> roleSet = new HashSet<String>();
			for (SysRole r : roles) {
				roleSet.add(r.getId());
			}
			currUser.setRoleSet(roleSet);

			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(currUser, loginPwd, getName());
			ShiroContext.setSessionAttribute(CURRENT_USER, currUser);
			return info;
		} catch (Exception e) {
			throw new AuthenticationException(e);
		}
	}
}
