package com.fsoft.manager.user.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsoft.core.Global;
import com.fsoft.core.annotation.SystemLog;
import com.fsoft.core.common.QueryParam;
import com.fsoft.core.common.base.BaseController;
import com.fsoft.core.common.validator.Assert;
import com.fsoft.core.shiro.ShiroContext;
import com.fsoft.core.utils.DateTimeUtils;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.RetVo;
import com.fsoft.core.utils.UUIDUtils;
import com.fsoft.manager.user.entity.SysUser;
import com.fsoft.manager.user.service.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @package com.fsoft.manager.user.controller
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-05-06
 * @copyright 2009-2019
 */
@Controller
@RequestMapping("/sys-user")
public class SysUserController extends BaseController {
	@Autowired
	private SysUserService userService;

	/**
	 * 所有用户列表
	 * 
	 * @user fish(it.fish2010@foxmail.com)
	 * @date 2019-10-09
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findList")
	public RetVo findList(@RequestParam Map<String, Object> params) throws Exception {
		QueryParam query = new QueryParam(params);
		// 非默认单位的时候，只允许查询本单位的用户
		String currOrgId = getUser().getCurrentOrgId();
		if (OgnlUtils.isEmpty(params.get("orgId")) && !Global.DEFAULT_ORG_ID.equalsIgnoreCase(currOrgId))
			query.setOrgId(currOrgId);
		Page<SysUser> page = PageHelper.startPage(query.getPage(), query.getLimit());
		List<SysUser> userList = userService.findList(query);
		return RetVo.ok(page.getTotal(), userList);
	}

	/**
	 * 跳转到添加页面
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public String list() {
		return "/fsoft-web/user/sysuser-list";
	}

	/**
	 * 跳转到添加页面
	 */
	@RequestMapping("/add")
	public String add() {
		return "/fsoft-web/user/sysuser-modify";
	}

	@RequestMapping("/edit/{id}")
	@RequiresPermissions("sys:user:edit")
	public String edit(HttpServletRequest request, Model model, @PathVariable("id") String id) throws Exception {
		SysUser user = userService.getEntity(id);
		model.addAttribute("user", user);
		return "/fsoft-web/user/sysuser-modify";
	}

	/**
	 * 获取登录的用户信息
	 */
	@ResponseBody
	@RequestMapping("/info")
	public RetVo info() {
		return RetVo.ok().put("user", getUser());
	}

	/***
	 * F-Soft 修改登录用户密码
	 * 
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-12-11
	 * @param password
	 * @param newPassword
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("修改密码")
	@RequestMapping("/password")
	public RetVo password(String password, String newPassword) throws Exception {
		Assert.isBlank(newPassword, "新密码不为能空");
		// sha256加密
		password = new Sha256Hash(password).toHex();
		// sha256加密
		newPassword = new Sha256Hash(newPassword).toHex();
		if (StringUtils.equals(newPassword, password))
			return RetVo.error("新密码与旧密码相同，请重新设置新密码 !");
		// 更新密码
		try {
			int count = userService.updatePassword(getUserId(), password, newPassword);
			if (count == 0)
				return RetVo.error("原密码不正确");
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
		// 退出
		ShiroContext.logout();
		return RetVo.ok();
	}

	/**
	 * F-Soft 用户详情
	 * 
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public RetVo info(@PathVariable("userId") String userId) throws Exception {
		SysUser user = userService.getEntity(userId);
		return RetVo.ok(1L, user);
	}

	/***
	 * F-Soft 保存用户(新增)
	 * 
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("保存用户")
	@RequestMapping("/save")
	public RetVo save(@RequestBody SysUser user) throws Exception {
		SysUser existUser = userService.getSysUserByLoginAcct(user.getLoginAcct());
		if (existUser != null) {
			return RetVo.error("用户名已存在!");
		}
		if (StringUtils.isBlank(user.getId()))
			user.setId(UUIDUtils.randomUpperCaseId());
		// 默认密码(Controller 不允许对密码加密)
		if (StringUtils.isBlank(user.getLoginPwd()))
			user.setLoginPwd(Global.SYS_USER_DEFAULE_PWD);
		if (OgnlUtils.isNotEmpty(user.getRoleIdList()))
			user.setRoleIdList(buildStrList(user.getRoleIdList()));
		// 增加ORGID标识
		if (StringUtils.isEmpty(user.getOrgId()))
			user.setOrgId(getUser().getCurrentOrgId());
		try {
			user.setCreateUserId(getUserId());
			user.setCreateTime(DateTimeUtils.getNowTime());
			user.setOrgId(getUser().getCurrentOrgId());
			user.setAreaId(getUser().getCurrentAreaId());
			userService.save(user);
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/***
	 * F-Soft 修改用户(更新)
	 * 
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-29
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("修改用户")
	@RequestMapping("/modify")
	public RetVo modify(@RequestBody SysUser user) throws Exception {
		if (StringUtils.equals(user.getId(), Global.SYS_USER_ADMIN_ID)) {
			user.setLoginAcct(null); // 如果是超级管理员帐号，不允许修改登录名
		}
		SysUser entity = userService.getEntity(user.getId());
		if (entity == null)
			return RetVo.error("用户信息已更新，请刷新后重试");
		if (OgnlUtils.isNotEmpty(user.getRoleIdList()))
			user.setRoleIdList(buildStrList(user.getRoleIdList()));
		if (StringUtils.isBlank(user.getOrgId()))
			user.setOrgId(getUser().getCurrentOrgId());
		try {
			user.setModifyUserId(getUserId());
			user.setModifyTime(DateTimeUtils.getNowTime());
			userService.modify(user);
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	@SystemLog("锁定账号")
	@ResponseBody
	@RequestMapping("/lock")
	@RequiresPermissions("sys:user:lock")
	public RetVo lock(@RequestBody String[] ids) throws Exception {
		List<SysUser> list = new ArrayList<SysUser>();
		try {
			for (String id : ids) {
				if (StringUtils.equals(id, getUserId()))
					throw new Exception("不允许对自己加锁，请重新选择！");
				SysUser user = new SysUser();
				user.setId(id);
				user.setLockType(Global.LOCK_TYPE_ADMIN);
				user.setLockTime(DateTimeUtils.getNowTime());
				user.setModifyUserId(getUserId());
				user.setModifyTime(DateTimeUtils.getNowTime());
				list.add(user);
			}
			userService.lockUser(list);
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/*****
	 * 账号解锁
	 * 
	 * @user fish(it.fish2010@foxmail.com)
	 * @date 2019-10-10
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("解锁帐号")
	@RequestMapping("/unlock")
	@RequiresPermissions("sys:user:unlock")
	public RetVo unlock(@RequestBody String[] ids) throws Exception {
		List<SysUser> list = new ArrayList<SysUser>();
		try {
			for (String id : ids) {
				if (StringUtils.equals(id, getUserId()))
					throw new Exception("不允许自己解锁，请联系其他管理员！");
				SysUser user = new SysUser();
				user.setId(id);
				user.setModifyUserId(getUserId());
				user.setModifyTime(DateTimeUtils.getNowTime());
				list.add(user);
			}
			userService.unlockUser(list);
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/***
	 * F-Soft 帐号启用
	 * 
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-16
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("帐号启用")
	@RequestMapping("/enable")
	@RequiresPermissions("sys:user:enable")
	public RetVo enable(@RequestBody String[] ids) throws Exception {
		try {
			for (String id : ids) {
				SysUser entity = new SysUser();
				entity.setId(id);
				entity.setStatus(Global.STATUS_YES);
				entity.setModifyUserId(getUserId());
				entity.setModifyTime(DateTimeUtils.getNowTime());
				userService.modify(entity);
			}
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/***
	 * F-Soft 帐号禁用
	 * 
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-16
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("帐号禁用")
	@RequestMapping("/disable")
	@RequiresPermissions("sys:user:enable")
	public RetVo disable(@RequestBody String[] ids) throws Exception {
		try {
			for (String id : ids) {
				SysUser entity = new SysUser();
				entity.setId(id);
				entity.setStatus(Global.STATUS_NO);
				entity.setModifyUserId(getUserId());
				entity.setModifyTime(DateTimeUtils.getNowTime());
				userService.modify(entity);
			}
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/**
	 * 删除用户
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("删除用户")
	@RequestMapping("/remove")
	@RequiresPermissions("sys:user:remove")
	public RetVo remove(@RequestBody String[] userIds) throws Exception {
		if (ArrayUtils.contains(userIds, Global.SYS_USER_ADMIN_ID)) {
			return RetVo.error("系统管理员不能删除");
		}
		if (ArrayUtils.contains(userIds, getUserId())) {
			return RetVo.error("当前用户[" + getLoginAcct() + "]已登录，不能删除!");
		}
		userService.removeBatch(Arrays.asList(userIds));
		return RetVo.ok();
	}

	/**
	 * 初始化密码
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("初始化密码")
	@RequestMapping("/initPassword")
	@RequiresPermissions("sys:user:init:password")
	public RetVo initPassword(@RequestBody String[] userIds) throws Exception {
		userService.initPassword(userIds);
		return RetVo.ok();
	}

	protected final List<String> buildStrList(List<String> list) {
		List<String> strList = new ArrayList<String>();
		for (String str : list) {
			String[] s = str.split(",");
			strList.addAll(Arrays.asList(s));
		}
		return strList;
	}

}
