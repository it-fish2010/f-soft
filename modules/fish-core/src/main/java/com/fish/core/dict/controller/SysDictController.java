package com.fish.core.dict.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fish.core.annotation.SystemLog;
import com.fish.core.common.QueryParam;
import com.fish.core.dict.entity.SysDict;
import com.fish.core.dict.service.SysDictService;
import com.fish.core.utils.RetVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * F-Soft 数据字典
 * @package com.fish.core.dict.controller
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-05
 * @CopyRight © F-Soft
 **/
@Controller
@RequestMapping("/sys-dict")
public class SysDictController {
	@Autowired
	private SysDictService dictService;

	/****
	 * F-Soft 字典列表入口
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-05
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list() throws Exception {
		return "fish-web/dict/sysdict-list";
	}

	/***
	 * F-Soft 字典明细配置列表
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-05
	 * @param model
	 * @param rwid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/itemlist/{rwid}")
	public String itemlist(Model model, @PathVariable("rwid") String rwid) throws Exception {
		model.addAttribute("rwid", rwid);
		return "fish-web/dict/sysdict-itemlist";
	}

	/***
	 * F-Soft 跳转到字典编辑界面
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-05
	 * @param model
	 * @param rwid 字典的RWID标识
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit/{rwid}")
	public String edit(Model model, @PathVariable("rwid") String rwid) throws Exception {
		SysDict sd = dictService.getEntity(rwid);
		model.addAttribute("model", sd);
		return "fish-web/dict/sysdict-modify";
	}

	/**
	 * F-Soft 字典列表查询
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-05
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findList")
	public RetVo findList(@RequestParam Map<String, Object> params) throws Exception {
		QueryParam query = new QueryParam(params);
		Page<SysDict> page = PageHelper.startPage(query.getPage(), query.getLimit());
		List<SysDict> list = dictService.findList(query);
		return RetVo.ok(page.getTotal(), list);
	}

	/**
	 * 保存
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("保存字典表")
	@RequestMapping("/save")
	@RequiresPermissions("sys:dict:save")
	public RetVo save(@RequestBody SysDict sysDict) throws Exception {

		dictService.save(sysDict);
		return RetVo.ok();
	}

	/**
	 * 修改
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("修改字典表")
	@RequestMapping("/update")
	@RequiresPermissions("sys:dict:update")
	public RetVo update(@RequestBody SysDict sysDict) throws Exception {
		dictService.modify(sysDict);

		return RetVo.ok();
	}

	/**
	 * 删除
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("删除字典表")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:dict:delete")
	public RetVo delete(@RequestBody String[] ids) throws Exception {
		dictService.removeBatch(Arrays.asList(ids));
		return RetVo.ok();
	}

}
