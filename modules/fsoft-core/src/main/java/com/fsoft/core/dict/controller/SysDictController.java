package com.fsoft.core.dict.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

import com.fsoft.core.Global;
import com.fsoft.core.annotation.SystemLog;
import com.fsoft.core.common.QueryParam;
import com.fsoft.core.common.base.BaseController;
import com.fsoft.core.dict.entity.SysDict;
import com.fsoft.core.dict.entity.SysDictItem;
import com.fsoft.core.dict.service.SysDictService;
import com.fsoft.core.utils.DateTimeUtils;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.RetVo;
import com.fsoft.core.utils.tree.Tree;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * F-Soft 数据字典
 * 
 * @package com.fsoft.core.dict.controller
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-05
 * @CopyRight © F-Soft
 **/
@Controller
@RequestMapping("/sys-dict")
public class SysDictController extends BaseController {
	@Autowired
	private SysDictService dictService;

	/**
	 * F-Soft 字典列表入口 <BR>
	 * @author licheng 2020-02-15
	 * @param model
	 * @param dictId 字典ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list() throws Exception {
		return "/fsoft-web/dict/sysdict-list";
	}

	/***
	 * F-Soft 跳转到字典配置项列表
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param model
	 * @param dictId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/items/{dictId}")
	public String items(Model model, @PathVariable("dictId") String dictId) throws Exception {
		SysDict dict = dictService.getEntity(dictId);
		model.addAttribute("model", dict);
		if (dict.getDictType().compareTo(new Integer("1")) == 0)
			return "/fsoft-web/dict/sysitems-list";
		else if (dict.getDictType().compareTo(new Integer("2")) == 0)
			return "/fsoft-web/dict/sysitems-trees";
		return "/fsoft-web/404";

	}

	/***
	 * F-Soft 跳转到字典编辑界面<br>
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-05
	 * @param model
	 * @param rwid 字典的RWID标识
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit/{rwid}")
	public String edit(Model model, @PathVariable("rwid") String rwid) throws Exception {
		SysDict dictEntity = dictService.getEntity(rwid);
		model.addAttribute("model", dictEntity);
		return "/fsoft-web/dict/sysdict-modify";
	}

	/**
	 * 
	 * F-Soft 跳转到字典编辑（新增）界面
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	public String add() throws Exception {
		return "/fsoft-web/dict/sysdict-modify";
	}

	/**
	 * 
	 * F-Soft 跳转到配置项编辑界面（新增）
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/additem/{dictId}")
	public String additem(Model model, @PathVariable("dictId") String dictId) throws Exception {
		model.addAttribute("dictId", dictId);
		return "/fsoft-web/dict/sysitems-modify";
	}

	/**
	 * F-Soft 跳转到配置项编辑界面（修改）
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param model
	 * @param rwid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edititem/{rwid}")
	public String edititem(Model model, @PathVariable("rwid") String rwid) throws Exception {
		SysDictItem item = dictService.getDictItem(rwid);
		if (OgnlUtils.isEmpty(item))
			return "/fsoft-web/404";
		model.addAttribute("dictId", item.getDictId());
		model.addAttribute("model", item);
		return "/fsoft-web/dict/sysitems-modify";
	}

	/**
	 * F-Soft 字典列表查询
	 * 
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

	/***
	 * F-Soft 字典配置项列表查询
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findItemList")
	public RetVo findItemList(@RequestParam Map<String, Object> params) throws Exception {
		QueryParam query = new QueryParam(params);
		Page<SysDictItem> page = PageHelper.startPage(query.getPage(), query.getLimit());
		List<SysDictItem> list = dictService.findDictItems(query);
		return RetVo.ok(page.getTotal(), list);
	}

	/***
	 * F-Soft 树形结构，返回配置项列表
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findItemTrees")
	public RetVo findItemTrees(@RequestParam Map<String, Object> params) throws Exception {
		QueryParam queryParam = new QueryParam(params);
		List<Tree> trees = dictService.findDictItemTree(queryParam);
		return RetVo.ok(trees.size(), trees);
	}

	/**
	 * F-Soft 保存字典操作(新增）
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("新增字典记录")
	@RequestMapping("/save")
	@RequiresPermissions("sys:dict:save")
	public RetVo save(@RequestBody SysDict param) throws Exception {
		try {
			if (OgnlUtils.isEmpty(param.getStatus()))
				param.setStatus(Global.STATUS_YES);
			param.setCreateTime(DateTimeUtils.getNowTime());
			param.setCreateUserId(getUserId());
			dictService.save(param);
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/**
	 * F-Soft 保存字典操作(更新）
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param sysDict
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("修改字典记录")
	@RequestMapping("/modify")
	@RequiresPermissions("sys:dict:modify")
	public RetVo update(@RequestBody SysDict sysDict) throws Exception {
		try {
			sysDict.setModifyUserId(getUserId());
			sysDict.setModifyTime(DateTimeUtils.getNowTime());
			dictService.modify(sysDict);
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/***
	 * F-Soft 删除字典记录
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("删除字典记录")
	@RequestMapping("/remove")
	@RequiresPermissions("sys:dict:remove")
	public RetVo remove(@RequestBody String[] ids) throws Exception {
		try {
			dictService.removeBatch(Arrays.asList(ids));
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	@ResponseBody
	@SystemLog("添加配置项")
	@RequestMapping("/saveItem")
	@RequiresPermissions("sys:dict:save")
	public RetVo saveItem(@RequestBody SysDictItem param) throws Exception {
		try {
			if (StringUtils.isBlank(param.getDictId()))
				throw new Exception("无法识别字典标识，请刷新后重试");
			SysDictItem db_item = dictService.getDictItem(param.getDictId(), param.getCode());
			if (OgnlUtils.isNotEmpty(db_item))
				throw new Exception("配置项编码已经存在，请重新修改");
			if (OgnlUtils.isEmpty(param.getStatus()))
				param.setStatus(Global.STATUS_YES);
			param.setCreateTime(DateTimeUtils.getNowTime());
			param.setCreateUserId(getUserId());
			dictService.saveItem(param);
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/**
	 * F-Soft 修改配置项
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("修改配置项")
	@RequestMapping("/modifyItem")
	@RequiresPermissions("sys:dict:modify")
	public RetVo modifyItem(@RequestBody SysDictItem param) throws Exception {
		try {
			if (StringUtils.isBlank(param.getDictId()))
				throw new Exception("无法识别字典标识，请刷新后重试");
			if (StringUtils.isBlank(param.getId()))
				throw new Exception("无法识别的配置项标识，请刷新后重试");
			param.setModifyUserId(getUserId());
			param.setModifyTime(DateTimeUtils.getNowTime());
			SysDictItem db_item = dictService.getDictItem(param.getDictId(), param.getCode());
			if (OgnlUtils.isNotEmpty(db_item) && !StringUtils.equals(db_item.getId(), param.getId()))
				throw new Exception("编码[" + param.getCode() + "]已被[" + db_item.getName() + "]使用，请重新修改");
			dictService.modifyItem(param);
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/**
	 * F-Soft 删除配置项
	 * @author it_software(it.fish2010@foxmail.com)
	 * @date 2020-02-15
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog("删除配置项")
	@RequestMapping("/removeItem")
	@RequiresPermissions("sys:dict:remove")
	public RetVo removeItem(@RequestBody String[] ids) throws Exception {
		try {
			dictService.removeBatch(Arrays.asList(ids));
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

}
