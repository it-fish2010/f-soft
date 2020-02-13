package com.fsoft.manager.menu.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.core.Global;
import com.fsoft.core.common.QueryParam;
import com.fsoft.core.common.service.impl.BaseServiceImpl;
import com.fsoft.core.utils.DateTimeUtils;
import com.fsoft.core.utils.OgnlUtils;
import com.fsoft.core.utils.UUIDUtils;
import com.fsoft.core.utils.tree.Tree;
import com.fsoft.manager.menu.entity.SysMenu;
import com.fsoft.manager.menu.mapper.SysMenuMapper;
import com.fsoft.manager.menu.service.SysMenuService;

@Service("sysMenuService")
@Transactional
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {
	@Autowired
	private SysMenuMapper menuMapper;

	@Override
	public int save(SysMenu param) throws Exception {
		if (StringUtils.isBlank(param.getId()))
			param.setId(UUIDUtils.randomUpperCaseId());
		if (OgnlUtils.isEmpty(param.getCreateTime()))
			param.setCreateTime(DateTimeUtils.getNowTime());
		// 维护Parents 字符串
		if (StringUtils.isBlank(param.getParentId())) {
			param.setParents(param.getId());
		} else {
			SysMenu p_menu = getEntity(param.getParentId());
			if (OgnlUtils.isNotEmpty(p_menu) && StringUtils.isNotBlank(p_menu.getParents()))
				param.setParents(p_menu.getParents() + "_" + param.getId());
		}
		// 自动配置超级管理员与菜单的关联关系
		menuMapper.insertMenuRole(UUIDUtils.randomUpperCaseId(), param.getId(), Global.SYS_ROLE_ADMIN_ID);
		return super.save(param);
	}

	@Override
	public int modify(SysMenu param) throws Exception {
		return super.modify(param);
	}

	@Override
	public List<Tree> findMenuTrees(QueryParam param) throws Exception {
		List<SysMenu> menuList = findList(param);
		return buildMenuTree(menuList);
	}

	/***
	 * F-Soft 把菜单对象SysMenu转换为Tree对象
	 * 
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-04
	 * @param list
	 * @return
	 * @throws Exception
	 */
	protected final List<Tree> buildMenuTree(List<SysMenu> menuList) throws Exception {
		List<Tree> trees = new ArrayList<Tree>();
		for (SysMenu menu : menuList) {
			Tree node = new Tree();
			node.setId(menu.getId());
			node.setField(menu.getCode());
			node.setTitle(menu.getName());
			node.setCode(menu.getPerms());
			node.setParentId(menu.getParentId());
			node.setIcon(menu.getIcon());
			node.setMenuType(menu.getMenuType());
			node.setHref(menu.getActionUrl());
			node.setPerms(menu.getPerms());
			trees.add(node);
		}
		// 维护Tree属性中的children
		for (Tree tree : trees) {
			List<Tree> chs = new ArrayList<Tree>();
			for (Tree ch : trees) {
				if (StringUtils.equals(tree.getId(), ch.getParentId()))
					chs.add(ch);
			}
			tree.setChildren(chs);
		}
		return trees;
	}

	@Override
	public List<Tree> findUserMenuTrees(String userId) throws Exception {
		QueryParam params = new QueryParam();
		params.put("userId", userId);
		List<SysMenu> menuList = findNotButtonList(params);
		return buildMenuTree(menuList);
	}

	@Override
	public List<SysMenu> findNotButtonList(QueryParam params) throws Exception {
		params.put("menuTypeNotIn", "2,3");
		return findList(params);
	}

	@Override
	public List<SysMenu> findByParentId(String pId) throws Exception {
		SysMenu menu = new SysMenu();
		menu.setParentId(pId);
		QueryParam queryParam = new QueryParam(menu);
		return findList(queryParam);
	}

	@Override
	public List<SysMenu> findUserMenuList(String userid) throws Exception {
		QueryParam qp = new QueryParam();
		qp.put("userId", userid);
		return findList(qp);
	}

	@Override
	public List<String> findUserPermList(String userId) throws Exception {
		List<SysMenu> list = findUserMenuList(userId);
		List<String> prems = new ArrayList<String>();
		for (SysMenu menu : list) {
			if (StringUtils.isNotBlank(menu.getPerms()))
				prems.addAll(Arrays.asList(menu.getPerms().trim().split(",")));
		}
		return prems;
	}

	@Override
	public int remove(String rwid) throws Exception {
		menuMapper.deleteMenuRole(rwid); // 删除菜单的授权关系（避免存在垃圾数据)
		return menuMapper.delete(rwid);

	}

}
