package com.fsoft.manager.area.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.fsoft.core.utils.DateTimeUtils;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.RetVo;
import com.fsoft.core.utils.UUIDUtils;
import com.fsoft.core.utils.tree.BuildTree;
import com.fsoft.manager.area.entity.SysArea;
import com.fsoft.manager.area.service.SysAreaService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * F-Soft 地区管理
 * @package com.fsoft.manager.area.controller
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2020-02-15
 * @CopyRight © F-Soft
 **/
@Controller
@RequestMapping("/sys-area")
public class SysAreaController extends BaseController {
	@Autowired
	private SysAreaService areaService;

	/***
	 * F-Soft
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findList")
	public RetVo findList(@RequestParam Map<String, Object> params) throws Exception {
		try {
			QueryParam query = new QueryParam(params);
			Page<SysArea> page = PageHelper.startPage(query.getPage(), query.getLimit());
			return RetVo.ok(page.getTotal(), areaService.findList(query));
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/***
	 * F-Soft 以树形方式返回地区列表
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findTrees")
	public RetVo findTrees(@RequestParam Map<String, Object> params) throws Exception {
		QueryParam queryParam = new QueryParam(params);
		List<SysArea> trees = areaService.findList(queryParam);
		return RetVo.ok(trees.size(), BuildTree.buildTree(trees));
	}

	@RequestMapping("/list")
	public String list() {
		return "/fsoft-web/area/sysarea-list";
	}

	/**
	 * 跳转到新增页面
	 **/
	@RequestMapping("/add")
	@RequiresPermissions("sys:area:add")
	public String add() {
		return "/fsoft-web/area/sysarea-modify";
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @throws Exception
	 **/
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("sys:area:edit")
	public String edit(HttpServletRequest request, Model model, @PathVariable("id") String id) throws Exception {
		SysArea area = areaService.getEntity(id);
		model.addAttribute("model", area);
		return "/fsoft-web/area/sysarea-modify";
	}

	/**
	 * F-Soft 保存地区信息（新增）
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param area
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("添加地区")
	@RequestMapping("/save")
	@RequiresPermissions("sys:area:save")
	public RetVo save(@RequestBody SysArea area) throws Exception {
		try {
			if (OgnlUtils.isEmpty(area.getId()))
				area.setId(UUIDUtils.randomUpperCaseId());
			// 设置排序
			if (OgnlUtils.isEmpty(area.getSortNo()))
				area.setSortNo(BigDecimal.ONE);
			if (OgnlUtils.isEmpty(area.getIsCity()))
				area.setIsCity(Global.STATUS_NO);
			area.setStatus(Global.STATUS_YES);
			area.setCreateUserId(getUserId());
			area.setCreateTime(DateTimeUtils.getNowTime());
			areaService.save(area);
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}

	}

	/**
	 * F-Soft 保存地区信息（编辑）
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param area
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("修改地区")
	@RequestMapping("/modify")
	@RequiresPermissions("sys:area:modify")
	public RetVo modify(@RequestBody SysArea area) throws Exception {
		try {
			area.setModifyUserId(getUserId());
			area.setModifyTime(DateTimeUtils.getNowTime());
			areaService.modify(area);
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/**
	 * F-Soft 删除地区
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param areaIds
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("删除地区")
	@RequestMapping("/remove")
	@RequiresPermissions("sys:area:remove")
	public RetVo remove(@RequestBody String[] areaIds) throws Exception {
		areaService.removeBatch(Arrays.asList(areaIds));
		return RetVo.ok();
	}

	/***
	 * F-Soft 启用地区
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("启用地区")
	@RequestMapping("/enable")
	@RequiresPermissions("sys:area:enable")
	public RetVo enable(@RequestBody String[] ids) throws Exception {
		try {
			areaService.submitState(Arrays.asList(ids), Global.STATUS_YES);
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/**
	 * F-Soft 禁用地区
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("禁用地区")
	@RequestMapping("/disable")
	@RequiresPermissions("sys:area:enable")
	public RetVo disable(@RequestBody String[] ids) throws Exception {
		try {
			areaService.submitState(Arrays.asList(ids), Global.STATUS_NO);
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}
}
