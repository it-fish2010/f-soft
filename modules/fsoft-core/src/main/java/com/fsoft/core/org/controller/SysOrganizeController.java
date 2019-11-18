package com.fsoft.core.org.controller;

import java.util.List;
import java.util.Map;

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
import com.fsoft.core.org.entity.SysOrganize;
import com.fsoft.core.org.service.OrganizeService;
import com.fsoft.core.utils.DateTimeUtils;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.RetVo;
import com.fsoft.core.utils.tree.BuildTree;
import com.fsoft.core.utils.tree.Tree;

/**
 * F-Soft 单位管理
 * @package com.fsoft.core.org.controller
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-15
 * @CopyRight © F-Soft
 **/
@Controller
@RequestMapping("/sys-org")
public class SysOrganizeController extends BaseController {
	@Autowired
	private OrganizeService orgService;

	/***
	 * F-Soft 跳转到组织机构管理列表界面
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-15
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list() throws Exception {
		return "/fsoft-web/org/sysorg-list";
	}

	/***
	 * F-Soft 单位列表查询
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-15
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findList")
	public List<SysOrganize> findList(@RequestParam Map<String, Object> params) throws Exception {
		QueryParam query = new QueryParam(params);
		List<SysOrganize> organizeList = orgService.findList(query);
		return organizeList;
	}

	/****
	 * F-Soft 返回组织机构树形，提供给eleTree控件的组织数据
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-16
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findOrgTrees")
	public RetVo findOrgTrees(@RequestParam Map<String, Object> params) throws Exception {
		String orgId = getUser().getCurrentOrgId();
		if (StringUtils.equals(Global.DEFAULT_ORG_ID, orgId))
			orgId = null;
		List<Tree> trees = orgService.findOrgTree(orgId);
		return RetVo.ok(trees.size(), BuildTree.buildJsonArray(trees));
	}

	/***
	 * F-Soft 跳转到新增页面
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-15
	 * @return
	 */
	@RequestMapping("/add")
	@RequiresPermissions("org:save")
	public String add() {
		return "/fsoft-web/org/sysorg-modify";
	}

	/***
	 * F-Soft 跳转到编辑界面
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-15
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("org:modify")
	public String edit(Model model, @PathVariable("id") String id) throws Exception {
		SysOrganize organize = orgService.getEntity(id);
		model.addAttribute("model", organize);
		return "/fsoft-web/org/sysorg-list";
	}

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{orgId}")
	@RequiresPermissions("org:info")
	public RetVo info(@PathVariable("orgId") String orgId) throws Exception {
		SysOrganize organize = orgService.getEntity(orgId);
		return RetVo.ok(1, organize);
	}

	/***
	 * F-Soft 组织机构新增请求
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-15
	 * @param params
	 * @return
	 */
	@ResponseBody
	@SystemLog("新增组织机构")
	@RequestMapping("/save")
	@RequiresPermissions("org:save")
	public RetVo save(@RequestBody SysOrganize params) throws Exception {
		try {
			SysOrganize orgFromDb = orgService.getOrgByCode(params.getOrgCode());
			if (OgnlUtils.isEmpty(orgFromDb))
				throw new Exception("组织机构编码已存在");
			params.setCreateUserId(getUserId());
			params.setCreateTime(DateTimeUtils.getNowTime());
			orgService.save(params);
			return RetVo.ok();
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@SystemLog("编辑组织机构")
	@RequestMapping("/modify")
	@RequiresPermissions("org:modify")
	public RetVo modify(@RequestBody SysOrganize orgParam) throws Exception {
		try {
			SysOrganize org = orgService.getOrgByCode(orgParam.getOrgCode());
			if (OgnlUtils.isNotEmpty(org) && !StringUtils.equalsIgnoreCase(org.getRwid(), orgParam.getRwid()))
				throw new Exception("组织机构编码已使用，请重新修改!");
			orgParam.setModifyUserId(getUserId());
			orgParam.setModifyTime(DateTimeUtils.getNowTime());
			orgService.modify(orgParam);
		} catch (Exception e) {

		}

		return RetVo.ok();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@SystemLog("删除单位")
	@RequestMapping("/remove")
	@RequiresPermissions("org:remove")
	public RetVo remove(@RequestBody String[] orgIds) throws Exception {
		for (String orgId : orgIds) {
			orgService.remove(orgId);
		}
		return RetVo.ok();
	}

}
