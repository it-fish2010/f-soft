package com.fsoft.manager.menu.service;

import java.util.List;

import com.fsoft.core.common.QueryParam;
import com.fsoft.core.common.service.BaseService;
import com.fsoft.core.utils.tree.Tree;
import com.fsoft.manager.menu.entity.SysMenu;

/**
 * F-Soft 菜单服务类
 * @package com.fsoft.manager.menu.service
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @date 2019-11-02
 * @des
 **/
public interface SysMenuService extends BaseService<SysMenu> {

	/****
	 * F-Soft 根据查询条件，返回菜单树形
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-04
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<Tree> findMenuTrees(QueryParam param) throws Exception;

	/****
	 * F-Soft 根据用户ID标识，查询用于的菜单、目录权限（暂时提供给前端构建导航菜单调用）
	 * @author Fish(it.fish2010@foxmail.com)
	 * @date 2019-11-04
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<Tree> findUserMenuTrees(String userId) throws Exception;

	/****
	 * F-Soft 获取所有菜单、目录
	 * @user Fish
	 * @date 2019-05-10
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<SysMenu> findNotButtonList(QueryParam params) throws Exception;

	/**
	 * F-Soft 根据上级菜单ID，查询菜单列表
	 * @author fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @param pId
	 * @return
	 * @throws Exception *
	 */
	List<SysMenu> findByParentId(String pId) throws Exception;

	/**
	 * F-Soft 根据用户ID标识，查询用户有权限访问的菜单列表
	 * @author fish(it.fish2010@foxmail.com)
	 * @date 2019-11-02
	 * @param userid
	 * @return
	 * @throws Exception *
	 */
	List<SysMenu> findUserMenuList(String userid) throws Exception;

	/***
	 * F-Soft 获取用户的权限标识(结果集和findUserMenuList相同，findUserPermList返回的是SysMenu.perms标识)
	 * @author Fish 2019-03-26
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<String> findUserPermList(String userId) throws Exception;
}
