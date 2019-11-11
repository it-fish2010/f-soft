package com.fish.core.org.controller;

import java.util.ArrayList;
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

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.fish.core.Global;
import com.fish.core.annotation.SystemLog;
import com.fish.core.common.QueryParam;
import com.fish.core.org.entity.Organize;
import com.fish.core.org.service.OrganizeService;
import com.fish.core.utils.OgnlUtils;
import com.fish.core.utils.PageUtils;
import com.fish.core.utils.RetVo;
import com.fish.core.utils.tree.BuildTree;
import com.fish.core.utils.tree.Tree;

/**
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-03-22
 * @copyright 佳乐软件股份有限公司© 2019-2019
 */
@Controller
@RequestMapping("/organize")
public class OrganizeController {
	@Autowired
	private OrganizeService organizeService;

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("organize:list")
	public RetVo list(@RequestParam Map<String, Object> params) throws Exception {
		try {
			// 查询列表数据
			QueryParam query = new QueryParam(params);
			List<Organize> organizeList = organizeService.queryList(query);
			if (OgnlUtils.isEmpty(organizeList))
				throw new Exception("组织机构数据为空");
			for (Organize org : organizeList) {
				String parentOrgId = org.getParentOrgId();
				if (StringUtils.equals(Global.NO, parentOrgId)) {
					org.setParentOrgName("");
					org.setParentOrgId("-");
				}
			}
			int total = organizeService.queryTotal(query);
			PageUtils pageUtil = new PageUtils(organizeList, total, query.getLimit(), query.getPage());
			return RetVo.ok().put("page", pageUtil);
		} catch (Exception e) {
			return RetVo.error(e.getMessage());
		}

	}

	/**
	 * 跳转到新增页面
	 **/
	@RequestMapping("/add")
	@RequiresPermissions("organize:save")
	public String add() {
		return "organize/add.jsp";
	}

	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@ResponseBody
	@RequestMapping("/select")
	public String select() throws Exception {
		List<Organize> organizeList = organizeService.getList();
		List<Tree> trees = new ArrayList<Tree>();
		for (Organize org : organizeList) {
			Tree tree = new Tree();
			tree.setId(org.getOrgId());
			tree.setCode(org.getOrgCode());
			tree.setTitle(org.getOrgName());
			tree.setParentId(org.getParentOrgId());
			trees.add(tree);
		}
		return JSON.toJSONString(BuildTree.buildJsonArray(trees));
	}

	/**
	 * 跳转到修改页面
	 **/
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("organize:update")
	public String edit(Model model, @PathVariable("id") String id) {
		Organize organize = organizeService.queryObject(id);
		model.addAttribute("model", organize);
		return "organize/edit.jsp";
	}

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{orgId}")
	@RequiresPermissions("organize:info")
	public RetVo info(@PathVariable("orgId") String orgId) {
		Organize organize = organizeService.queryObject(orgId);
		return RetVo.ok().put("organize", organize);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@SystemLog("保存记录组织机构管理信息")
	@RequestMapping("/save")
	@RequiresPermissions("organize:save")
	public RetVo save(@RequestBody Organize organize) {
		// 判断部门编号是否存在
		Organize organize2 = organizeService.queryByOrgCode(organize.getOrgCode());
		if (OgnlUtils.isNotEmpty(organize2)) {
			return RetVo.error("部门编号已存在");
		}
		organizeService.save(organize);
		return RetVo.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@SystemLog("修改记录组织机构管理信息")
	@RequestMapping("/update")
	@RequiresPermissions("organize:update")
	public RetVo update(@RequestBody Organize organize) {

		organizeService.update(organize);

		return RetVo.ok();
	}

	/**
	 * 启用
	 */
	@ResponseBody
	@SystemLog("启用记录组织机构管理信息")
	@RequestMapping("/enable")
	@RequiresPermissions("organize:update")
	public RetVo enable(@RequestBody String[] ids) {
		String stateValue = Global.YES;
		organizeService.updateState(ids, stateValue);
		return RetVo.ok();
	}

	/**
	 * 禁用
	 */
	@ResponseBody
	@SystemLog("禁用记录组织机构管理信息")
	@RequestMapping("/limit")
	@RequiresPermissions("organize:update")
	public RetVo limit(@RequestBody String[] ids) {
		String stateValue = Global.NO;
		organizeService.updateState(ids, stateValue);
		return RetVo.ok();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@SystemLog("删除记录组织机构管理信息")
	@RequestMapping("/delete")
	@RequiresPermissions("organize:delete")
	public RetVo delete(@RequestBody String[] orgIds) {

		for (String orgId : orgIds) {
			organizeService.delete(orgId);
		}

		return RetVo.ok();
	}

}
