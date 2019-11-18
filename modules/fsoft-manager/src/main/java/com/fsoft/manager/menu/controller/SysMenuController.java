package com.fsoft.manager.menu.controller;

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
import com.fsoft.core.common.exceptions.RRException;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.RetVo;
import com.fsoft.core.utils.tree.BuildTree;
import com.fsoft.core.utils.tree.Tree;
import com.fsoft.manager.menu.entity.SysMenu;
import com.fsoft.manager.menu.service.SysMenuService;

/**
 * F-Soft 菜单管理Controller，实现对菜单、目录的维护
 * @package com.fsoft.manager.menu.controller
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-02
 * @CopyRight © F-Soft
 **/
@Controller
@RequestMapping("/sys-menu")
public class SysMenuController extends BaseController {
	@Autowired
	private SysMenuService sysMenuService;

	/***
	 * F-Soft 跳转到
	 * @user Fish
	 * @date 2019-05-09
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public String list() throws Exception {
		return "/fsoft-web/menu/sysmenu-list";
	}

	/****
	 * F-Soft 菜单列表查看
	 * @author Fish it.fish2010@foxmail.com
	 * @date 2019-11-01
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findList")
	public List<SysMenu> findList(@RequestParam Map<String, Object> params) throws Exception {
		QueryParam query = new QueryParam(params);
		List<SysMenu> list = sysMenuService.findList(query);
		return list;
	}

	/***
	 * F-Soft 跳转到菜单详情页面
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @param menuId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/info/{menuId}")
	@RequiresPermissions("sys:menu:info")
	public RetVo info(@PathVariable("menuId") String menuId) throws Exception {
		SysMenu menu = sysMenuService.getEntity(menuId);
		return RetVo.ok().put("menu", menu);
	}

	/***
	 * F-Soft 页面跳转，跳转到菜单/目录的表单填写页面
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @return
	 */
	@RequestMapping("/add")
	@RequiresPermissions("sys:menu:add")
	public String add() {
		return "/fsoft-web/menu/sysmenu-modify";
	}

	/**
	 * F-Soft 跳转到菜单编辑页面
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("sys:menu:edit")
	public String edit(HttpServletRequest request, Model model, @PathVariable("id") String id) throws Exception {
		SysMenu sysMenu = sysMenuService.getEntity(id);
		model.addAttribute("menu", sysMenu);
		return "/fsoft-web/menu/sysmenu-modify";
	}

	/***
	 * F-Soft 响应菜单新增的保存操作
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("添加菜单")
	@RequestMapping("/save")
	public RetVo save(@RequestBody SysMenu menu) throws Exception {
		// 数据校验
		verifyForm(menu);
		sysMenuService.save(menu);
		return RetVo.ok();
	}

	/***
	 * F-Soft 响应菜单编辑/修改的保存操作
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("修改菜单")
	@RequestMapping("/modify")
	public RetVo modify(@RequestBody SysMenu menu) throws Exception {
		String id = menu.getId();
		if (StringUtils.isBlank(id))
			throw new RRException("菜单标识为空");
		// 数据校验
		verifyForm(menu);
		if (StringUtils.isBlank(menu.getId()) && StringUtils.isNotBlank(id))
			menu.setId(id);
		sysMenuService.modify(menu);
		return RetVo.ok();
	}

	/***
	 * F-Soft 响应菜单删除的操作，支持批量删除
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @param menuIds
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("删除菜单")
	@RequestMapping("/remove")
	@RequiresPermissions("sys:menu:remove")
	public RetVo remove(@RequestBody String[] menuIds) throws Exception {
		try {
			if (OgnlUtils.isEmpty(menuIds))
				throw new RRException("请传入菜单标识");
			sysMenuService.removeBatch(Arrays.asList(menuIds));
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/**
	 * F-Soft 响应前端请求-获取当前登录用户的所有目录、菜单的列表，用于组装首页的功能导航菜单
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/user")
	public RetVo user() throws Exception {
		List<Tree> trees = sysMenuService.findUserMenuTrees(getUserId());
		return RetVo.ok().put("menulist", BuildTree.buildJsonArray(trees));
	}

	/***
	 * F-Soft 菜单的表单数据校验
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @param menu
	 * @throws Exception
	 */
	protected final void verifyForm(SysMenu menu) throws Exception {
		if (StringUtils.isBlank(menu.getName()))
			throw new RRException("菜单名称不能为空");
		// 如果是“功能菜单”，actionUrl不允许为空
		if (menu.getMenuType() == Global.MENU_TYPE_MENU && StringUtils.isBlank(menu.getActionUrl()))
			throw new RRException("菜单URL不能为空");
		if (StringUtils.isNotBlank(menu.getParentId()) && !StringUtils.equalsIgnoreCase("0", menu.getParentId())) {
			SysMenu parentMenu = sysMenuService.getEntity(menu.getParentId());
			if (OgnlUtils.isEmpty(parentMenu))
				throw new RRException("无法识别上级菜单/目录标识（" + menu.getParentId() + "）");
			//目录、菜单的上级，只允许是“目录”。
			if (menu.getMenuType() == Global.MENU_TYPE_CATALOG || menu.getMenuType() == Global.MENU_TYPE_MENU) {
				if (parentMenu.getMenuType() != Global.MENU_TYPE_CATALOG) {
					throw new RRException("上级菜单只能为目录类型");
				}
			} else if (menu.getMenuType() == Global.MENU_TYPE_BUTTON && parentMenu.getMenuType() == Global.MENU_TYPE_BUTTON) {
				throw new RRException("按钮的上级菜单不允许是按钮");
			}
		}
	}
}
