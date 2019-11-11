package com.fish.manager.area.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fish.core.Global;
import com.fish.core.annotation.SystemLog;
import com.fish.core.common.BaseData;
import com.fish.core.common.QueryParam;
import com.fish.core.utils.OgnlUtils;
import com.fish.core.utils.PinYinUtils;
import com.fish.core.utils.RetVo;
import com.fish.core.utils.UUIDUtils;
import com.fish.manager.area.entity.SysArea;
import com.fish.manager.area.service.SysAreaService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("area")
public class SysAreaController {
	@Autowired
	private SysAreaService areaService;

	/****
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findList")
	@RequiresPermissions("area:list")
	public RetVo findList(@RequestParam Map<String, Object> params) throws Exception {
		QueryParam query = new QueryParam(params);
		try {
			Page<SysArea> page = PageHelper.startPage(query.getPage(), query.getLimit());
			return RetVo.ok(page.getTotal(), areaService.findList(query));
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/**
	 * 获取下级地区
	 */
	@ResponseBody
	// @RequiresPermissions("area:list")
	@RequestMapping("normalList/{parentAreaId}")
	public RetVo normalList(@PathVariable String parentAreaId) throws Exception {
		List<BaseData> list = new ArrayList<BaseData>();
		List<SysArea> areaList = areaService.findAreaListByIsShow(parentAreaId, Global.STATE_YES);
		if (OgnlUtils.isNotEmpty(areaList)) {
			for (SysArea ent : areaList) {
				BaseData bean = new BaseData();
				bean.setCode(ent.getId());
				bean.setValue(ent.getName());
				list.add(bean);
			}
		}
		return RetVo.ok().put("data", list);
	}

	@ResponseBody
	@RequestMapping("findByParentId")
	public RetVo findByParentId(@RequestParam Map<String, Object> params) throws Exception {
		String parentId = (String) params.get("parentId");
		if (StringUtils.isBlank(parentId)) {
			parentId = "0000000000";
		}
		List<SysArea> areaList = areaService.findByParentId(parentId);
		return RetVo.ok().put("data", areaList);
	}

	/**
	 * 跳转到新增页面
	 **/
	@RequestMapping("/add")
	@RequiresPermissions("area:save")
	public String add() {
		return "area/add.jsp";
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @throws Exception
	 **/
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("area:update")
	public String edit(HttpServletRequest request, Model model, @PathVariable("id") String id) throws Exception {
		SysArea area = areaService.getEntity(id);
		model.addAttribute("model", area);
		return "area/edit.jsp";
	}

	/**
	 * 信息
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/info/{areaId}")
	@RequiresPermissions("area:info")
	public RetVo info(@PathVariable("areaId") String areaId) throws Exception {
		SysArea area = areaService.getEntity(areaId);
		return RetVo.ok().put("area", area);
	}

	/**
	 * 保存
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("area:save")
	public RetVo save(@RequestBody SysArea area) throws Exception {
		if (OgnlUtils.isEmpty(area.getId()))
			area.setId(UUIDUtils.randomUpperCaseId());
		// 设置拼音
		String pinyin = PinYinUtils.getPingYin(area.getName().trim()).trim();
		area.setAreaNamePy(pinyin);
		// 设置排序
		area.setSortNo(BigDecimal.ZERO);
		area.setIsCity(Global.STATE_NO);

		String parentId = area.getParentAreaId();

		// 设置行政级别（上级行政级别+1）
		SysArea parent = areaService.getEntity(parentId.substring(parentId.length() - 10, parentId.length()));
		int parent_level = 0;
		if (OgnlUtils.isNotEmpty(parent)) {
			parent_level = parent.getAreaLevel();
		}
		if (parent_level == -1) {
			parent_level = 0;
		} else {
			parent_level = parent_level + 1;
		}
		area.setAreaLevel(parent_level);
		area.setStatus(area.getStatus());
		areaService.save(area);
		return RetVo.ok();

	}

	/**
	 * 修改
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("area:update")
	public RetVo update(@RequestBody SysArea area) throws Exception {
		SysArea oldArea = areaService.getEntity(area.getId());
		area.setId(oldArea.getId());
		areaService.modify(area);
		return RetVo.ok();
	}

	/**
	 * 删除
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("area:delete")
	public RetVo delete(@RequestBody String[] areaIds) throws Exception {
		areaService.removeBatch(Arrays.asList(areaIds));
		return RetVo.ok();
	}

	/**
	 * 启用
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("启用地区")
	@RequestMapping("/enable")
	@RequiresPermissions("area:update")
	public RetVo enable(@RequestBody String[] ids) throws Exception {
		String stateValue = Global.YES;
		areaService.submitState(Arrays.asList(ids), Integer.parseInt(stateValue));
		return RetVo.ok();
	}

	/**
	 * 禁用
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("禁用地区")
	@RequestMapping("/limit")
	@RequiresPermissions("area:update")
	public RetVo limit(@RequestBody String[] ids) throws Exception {
		String stateValue = Global.NO;
		areaService.submitState(Arrays.asList(ids), Integer.parseInt(stateValue));
		return RetVo.ok();
	}
}
