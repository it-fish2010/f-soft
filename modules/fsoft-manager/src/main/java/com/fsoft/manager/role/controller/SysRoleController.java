package com.fsoft.manager.role.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.fsoft.core.common.validator.ValidatorUtils;
import com.fsoft.core.utils.DateTimeUtils;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.RetVo;
import com.fsoft.core.utils.tree.Tree;
import com.fsoft.manager.menu.entity.SysMenu;
import com.fsoft.manager.menu.service.SysMenuService;
import com.fsoft.manager.role.entity.SysRole;
import com.fsoft.manager.role.service.SysRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-03-26
 * @copyright 佳乐软件股份有限公司© 2019-2019
 */
@Controller
@RequestMapping("/sys-role")
public class SysRoleController extends BaseController {
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private SysMenuService menuService;

	@RequestMapping("/list")
	public String list() throws Exception {
		return "/fsoft-web/role/sysrole-list";
	}

	/**
	 * 角色列表
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findList")
	@RequiresPermissions("sys:role:list")
	public RetVo findList(@RequestParam Map<String, Object> params) throws Exception {
		// 查询列表数据
		QueryParam query = new QueryParam(params);
		Page<SysRole> page = PageHelper.startPage(query.getPage(), query.getLimit());
		List<SysRole> list = roleService.findList(query);
		return RetVo.ok(page.getTotal(), list);
	}

	/**
	 * @des 跳转到新增角色界面
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-11-01
	 * @return
	 */
	@RequestMapping("/add")
	@RequiresPermissions("sys:role:add")
	public String add() {
		return "/fsoft-web/role/sysrole-modify";
	}

	/***
	 * @des 跳转到角色编辑界面
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-11-01
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("sys:role:edit")
	public String edit(HttpServletRequest request, Model model, @PathVariable("id") String id) throws Exception {
		SysRole role = roleService.getEntity(id);
		model.addAttribute("role", role);
		return "/fsoft-web/role/sysrole-modify";
	}

	/**
	 * @des 角色列表，提供给“分配角色”的时候调用。<BR>
	 *      任何人只允许选择自己已有的角色进行分配，自己没有的角色，不允许分配
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-11-01
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findRoleListByUser")
	public RetVo findRoleListByUser() throws Exception {
		List<SysRole> list = null;
		if (Global.SYS_USER_ADMIN_ID.equals(getUserId())) {
			list = roleService.findList(new QueryParam()); // 如果是admin，超级管理员，允许查看所有角色列表
		} else
			list = roleService.findRoleListByUserId(getUserId());
		return RetVo.ok(list.size(), list);
	}

	/***
	 * F-Soft 查看角色详情
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-04
	 * @param request
	 * @param model
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public String info(HttpServletRequest request, Model model, @PathVariable("roleId") String roleId)
			throws Exception {
		SysRole role = roleService.getEntity(roleId);
		model.addAttribute("role", role);
		return "/fsoft-web/role/sysrole-info";
	}

	/**
	 * F-Soft 返回已选角色拥有的菜单权限标识(查看角色权限的时候调用)
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-04
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findRoleMenuTrees")
	public RetVo findRoleMenuTrees(@RequestParam Map<String, Object> params) throws Exception {
		QueryParam query = new QueryParam();
		query.put("userId", getUserId());
		List<Tree> trees = menuService.findMenuTrees(query); // 查询当前用户的ID标识
		RetVo rv = RetVo.ok();
		rv.put("menutree", trees);
		//
		query = null;
		query = new QueryParam(params);
		List<SysMenu> roleMenus = menuService.findList(query);
		rv.put("rolemenu", roleMenus);
		return rv;
	}

	/***
	 * @des 新增角色保存
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-11-01
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("新增角色")
	@RequestMapping("/save")
	public RetVo save(@RequestBody SysRole role) throws Exception {
		ValidatorUtils.validateEntity(role);
		SysRole sr = roleService.getRoleByCode(role.getCode());
		if (sr != null) {
			return RetVo.error("角色编码已存在,请 重新修改");
		} else {
			role.setCreateUserId(getUserId());
			role.setCreateTime(DateTimeUtils.getNowTime());
			roleService.save(role);
		}
		return RetVo.ok();
	}

	/**
	 * @des 修改角色保存
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-11-01
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("修改角色")
	@RequestMapping("/modify")
	public RetVo modify(@RequestBody SysRole role) throws Exception {
		ValidatorUtils.validateEntity(role);
		SysRole sr = roleService.getRoleByCode(role.getCode());
		if (sr != null && !StringUtils.equals(role.getId(), sr.getId()))
			return RetVo.error("角色编码已被（" + sr.getName() + "）占用");
		role.setModifyTime(DateTimeUtils.getNowTime());
		role.setModifyUserId(getUserId());
		roleService.modify(role);
		return RetVo.ok();
	}

	/***
	 * 启用角色/禁用角色
	 * @user Fish
	 * @date 2019-05-08
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("启用/禁用 角色 ")
	@RequestMapping("/enable")
	public RetVo enable(@RequestBody SysRole role) throws Exception {
		ValidatorUtils.validateEntity(role);
		role.setModifyTime(DateTimeUtils.getNowTime());
		role.setModifyUserId(getUserId());
		roleService.modify(role);
		return RetVo.ok();
	}

	/**
	 * 删除角色
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("删除角色")
	@RequestMapping("/remove")
	@RequiresPermissions("sys:role:remove")
	public RetVo remove(@RequestBody String[] roleIds) throws Exception {
		if (OgnlUtils.isEmpty(roleIds))
			return RetVo.error("角色标识为空");
		try {
			roleService.removeBatch(Arrays.asList(roleIds));
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error("删除失败，" + e.getMessage());
		}
	}
}
